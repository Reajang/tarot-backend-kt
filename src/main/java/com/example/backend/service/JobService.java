package com.example.backend.service;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import com.example.backend.dto.system.JobDto;
import java.util.List;
import java.util.UUID;

public interface JobService {

    UUID createNew(JobType jobType);

    JobDto get(UUID jobId);

    void update(UUID jobId, JobStatus newStatus);

    void update(UUID jobId, JobStatus newStatus, List<Object> results);

    void setErrors(UUID jobId, List<Throwable> errors);

    void setErrorsAsStrings(UUID jobId, List<String> errorsInfo);
}
