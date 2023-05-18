package com.example.backend.dto.system;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import java.time.Instant;
import java.util.UUID;

public record JobDto(
    UUID id,
    Instant createDate,
    Instant updateDate,
    JobType type,
    JobStatus status
) {
}
