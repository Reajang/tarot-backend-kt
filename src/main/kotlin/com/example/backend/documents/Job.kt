package com.example.backend.documents

import com.example.backend.domain.system.JobStatus
import com.example.backend.domain.system.JobType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*


@Document
class Job {

    @Id
    var id: UUID? = null

    var createDate: Instant? = null

    var updateDate: Instant? = null

    var type: JobType? = null

    var status: JobStatus? = null

    var results: MutableList<JobResult>? = null

    constructor() {}

    constructor(
        id: UUID?,
        createDate: Instant?,
        updateDate: Instant?,
        type: JobType?,
        status: JobStatus?,
        results: MutableList<JobResult>?
    ) {
        this.id = id
        this.createDate = createDate
        this.updateDate = updateDate
        this.type = type
        this.status = status
        this.results = results
    }
}