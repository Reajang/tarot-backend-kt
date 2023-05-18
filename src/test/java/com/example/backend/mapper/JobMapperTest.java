package com.example.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.domain.system.Job;
import com.example.backend.domain.system.JobStatus;
import com.example.backend.domain.system.JobType;
import com.example.backend.dto.system.JobDto;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JobMapperTest {

    @Test
    void mapToDtoTest() {
        Job job = new Job(
            UUID.randomUUID(),
            Instant.now(),
            Instant.now(),
            JobType.TAROT_FUTURE_TELL,
            JobStatus.RUNNING
        );

        JobDto dto = assertDoesNotThrow(() -> JobMapper.INSTANCE.map(job));

        assertNotNull(dto);
        assertEquals(job.getId(), dto.id());
        assertEquals(job.getCreateDate(), dto.createDate());
        assertEquals(job.getUpdateDate(), dto.updateDate());
        assertEquals(job.getType(), dto.type());
        assertEquals(job.getStatus(), dto.status());
    }
}
