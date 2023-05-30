package com.example.backend.service;

import com.example.backend.domain.system.Job;
import com.example.backend.domain.system.JobResult;
import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import com.example.backend.dto.system.JobDto;
import com.example.backend.exceptions.ResourceNotFound;
import com.example.backend.mapper.JobMapper;
import com.example.backend.repository.JobRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public UUID createNew(JobType jobType) {
        Job newJob = Job.newIdle(jobType);
        repository.save(newJob);
        return newJob.getId();
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public JobDto get(UUID jobId) {
        return jobId != null
            ? repository.findById(jobId)
            .map(JobMapper.INSTANCE::map)
            .orElse(null)
            : null;
    }

    @Override
    @Transactional
    public void update(UUID jobId, JobStatus newStatus) {
        log.info("Try to update status job with id={}, new status={}", jobId, newStatus);
        repository.findById(jobId)
            .ifPresentOrElse(job -> {
                    job.setStatus(newStatus);
                    log.info("Job id={} successfully updated to {}", jobId, newStatus);
                },
                () -> log.error("Job id={} doesn't exists", jobId));

    }

    @Override
    @Transactional
    public void update(UUID jobId, JobStatus newStatus, List<Object> results) {
        Optional<Job> optionalJob = repository.findById(jobId);
        if (optionalJob.isEmpty()) {
            throw new ResourceNotFound(Job.class.getName(), jobId.toString());
        }
        Job job = optionalJob.get();

        List<JobResult> jobResults = results.stream()
            .map(resultData -> {
                JobResult jobResult = new JobResult();
                jobResult.setJob(job);
                String preparedForSavingResultData;
                String resultDataType = null;
                try {
                    preparedForSavingResultData = objectMapper.writeValueAsString(resultData);
                    //TODO  Тут кафка теряет тип объекта при пересылке объектов типа List<Object>. Всегда поолучается из json -> LinkedHashMap
                    resultDataType = resultData.getClass().getCanonicalName();
                } catch (JsonProcessingException e) {
                    log.warn("Can not write result data to job{} as JSON. Data will be saved as string. Data:\n{}", jobId, resultData);
                    preparedForSavingResultData = resultData.toString();
                }
                jobResult.setData(preparedForSavingResultData);
                jobResult.setType(ObjectUtils.defaultIfNull(resultDataType, "text"));
                return jobResult;
            })
            .toList();

        job.setStatus(newStatus);
        job.getResults().addAll(jobResults);
        repository.save(job);
    }

    @Override
    @Transactional
    public void setErrors(UUID jobId, List<Throwable> errors) {
        setErrorsAsStrings(
            jobId,
            errors.stream()
                .map(Throwable::getMessage)
                .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public void setErrorsAsStrings(UUID jobId, List<Object> errorsInfo) {
        Optional<Job> optionalJob = repository.findById(jobId);
        if (optionalJob.isEmpty()) {
            throw new ResourceNotFound(Job.class.getName(), jobId.toString());
        }
        Job job = optionalJob.get();
        List<JobResult> jobResults = errorsInfo.stream()
            .filter(Objects::nonNull)
            .map(errorText -> new JobResult(job, errorText.toString(), "text"))
            .collect(Collectors.toList());
        job.setStatus(JobStatus.ERROR);
        job.setResults(jobResults);
        repository.save(job);
    }
}
