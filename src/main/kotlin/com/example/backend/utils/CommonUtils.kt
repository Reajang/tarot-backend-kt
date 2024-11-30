package com.example.backend.utils

import com.example.backend.documents.Job
import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import mu.KotlinLogging
import java.time.Instant
import java.util.*

val LOGGER = KotlinLogging.logger {}


fun newIdleJob(jobType: JobType?): Job {
    val newJob = Job()
    newJob.id = UUID.randomUUID()
    newJob.createDate = Instant.now()
    newJob.type = jobType
    newJob.status = JobStatus.IDLE
    return newJob
}
