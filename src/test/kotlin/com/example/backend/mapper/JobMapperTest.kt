package com.example.backend.mapper

import com.example.backend.documents.Job
import com.example.backend.documents.JobResult
import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import com.example.backend.dto.system.JobDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.Instant
import java.util.*

class JobMapperTest {

    private var jobMapper: JobMapper = JobMapperImpl()

    @Test
    fun mapToDtoTest() {
        val job = Job(
            UUID.randomUUID(),
            Instant.now(),
            Instant.now(),
            JobType.TAROT_FUTURE_TELL,
            JobStatus.RUNNING
        )
        val jobResult = JobResult(
            UUID.randomUUID(),
            Instant.now(),
            "data",
            "type",
        )
        job.results = mutableListOf(jobResult)

        val dto: JobDto? = assertDoesNotThrow { jobMapper.map(job) }

        assertNotNull(dto)
        assertEquals(job.id, dto?.id)
        assertEquals(job.createDate, dto?.createDate)
        assertEquals(job.updateDate, dto?.updateDate)
        assertEquals(job.type, dto?.type)
        assertEquals(job.status, dto?.status)
        assertEquals(job.results!![0].data, dto?.results!![0].data)
        assertEquals(job.results!![0].type, dto.results!![0].type)
    }
}