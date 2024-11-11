package com.example.backend.domain.system;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job  {

    @Id
    protected UUID id;

    protected Instant createDate;

    protected Instant updateDate;

    private JobType type;

    private JobStatus status;

    private List<JobResult> results;

    public static Job newIdle(JobType jobType) {
        Job newJob = new Job();
        newJob.setId(UUID.randomUUID());
        newJob.setCreateDate(Instant.now());
        newJob.setType(jobType);
        newJob.setStatus(JobStatus.IDLE);
        return newJob;
    }
}
