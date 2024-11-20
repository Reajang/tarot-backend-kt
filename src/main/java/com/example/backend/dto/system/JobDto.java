package com.example.backend.dto.system;

import com.example.backend.documents.JobStatus;
import com.example.backend.documents.JobType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record JobDto(
    UUID id,
    Instant createDate,
    Instant updateDate,
    JobType type,
    JobStatus status,
    List<JobResultDto> results
) {
}
