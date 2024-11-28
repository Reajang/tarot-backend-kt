package com.example.backend.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*


@Document
data class Job(
    @Id
    var id: UUID? = null,
    var createDate: Instant? = null,
    var updateDate: Instant? = null,
    var type: JobType? = null,
    var status: JobStatus? = null,
    var results: MutableList<JobResult>? = null
)