package com.example.backend.dto.system

import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import java.time.Instant
import java.util.*

data class JobDto(
    var id: UUID?,
    var createDate: Instant?,
    var updateDate: Instant?,
    var type: JobType?,
    var status: JobStatus?,
    var results: List<JobResultDto>?
)


