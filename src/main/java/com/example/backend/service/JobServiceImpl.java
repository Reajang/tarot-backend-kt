package com.example.backend.service;

import com.example.backend.domain.system.Job;
import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import com.example.backend.dto.system.JobDto;
import com.example.backend.mapper.JobMapper;
import com.example.backend.repository.JobRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JobServiceImpl implements JobService {

    private final JobRepository repository;

    @Override
    public UUID createNew(JobType jobType) {
        Job newJob = Job.newIdle(jobType);
        repository.save(newJob);
        return newJob.getId();
    }

    @Override
    public JobDto get(UUID jobId) {
        return repository.findById(jobId)
            .map(JobMapper.INSTANCE::map)
            .orElse(null);
    }

    @Override
    public void update(UUID jobId, JobStatus newStatus) {
        log.info("Try to update status job with id={}, new status={}", jobId, newStatus);
        repository.findById(jobId)
            .ifPresentOrElse(job -> {
                    job.setStatus(newStatus);
                    log.info("Job id={} successfully updated", jobId);
                },
                () -> log.error("Job id={} doesn't exists", jobId));

    }
}
