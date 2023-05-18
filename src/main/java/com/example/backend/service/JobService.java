package com.example.backend.service;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import com.example.backend.dto.system.JobDto;
import java.util.UUID;

public interface JobService {

    UUID createNew(JobType jobType);

    JobDto get(UUID jobId);

    void update(UUID jobId, JobStatus newStatus);
}
