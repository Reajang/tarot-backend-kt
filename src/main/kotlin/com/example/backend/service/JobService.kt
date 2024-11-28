package com.example.backend.service

import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import com.example.backend.dto.system.JobDto
import reactor.core.publisher.Mono
import java.util.*

interface JobService {

    fun createNew(jobType: JobType): Mono<JobDto?>

    fun get(jobId: UUID): Mono<JobDto?>

    fun update(jobId: UUID, newStatus: JobStatus?)

    fun update(jobId: UUID, newStatus: JobStatus?, results: List<Any?>?)

    fun setErrors(jobId: UUID, errorsInfo: List<Any?>?)

}