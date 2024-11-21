package com.example.backend.service

import com.example.backend.documents.Job
import com.example.backend.documents.JobResult
import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import com.example.backend.dto.system.JobDto
import com.example.backend.events.tarot.TarotPredictionRequestEvent
import com.example.backend.exceptions.ResourceNotFound
import com.example.backend.mapper.JobMapper
import com.example.backend.repos.JobRepository
import com.example.backend.utils.LOGGER
import com.example.backend.utils.newIdleJob
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.ObjectUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*

@Service
class JobServiceImpl(
    val repository: JobRepository,
    val objectMapper: ObjectMapper
) : JobService {


    override fun createNew(jobType: JobType): UUID {
        val newJob = newIdleJob(jobType)
        repository.save(newJob)
        return newJob.id!!
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    override fun get(jobId: UUID): JobDto? {
        return repository.findById(jobId)
            .map { domain: Job ->
                JobMapper.INSTANCE.map(domain)
            }
            .orElseThrow { ResourceNotFound(Job::class.simpleName!!, jobId.toString()) }
    }

    @Transactional
    override fun update(jobId: UUID, newStatus: JobStatus?) {
        LOGGER.info { "Try to update status job with id=$jobId, new status=$newStatus" }
        repository.findById(jobId)
            .ifPresentOrElse(
                { job: Job ->
                    job.status = newStatus
                    LOGGER.info { "Job id=$jobId successfully updated to $newStatus" }
                },
                { LOGGER.error { "Job id=$jobId doesn't exists" } })
    }

    @Transactional
    override fun update(jobId: UUID, newStatus: JobStatus?, results: List<Any?>?) {
        val optionalJob = repository.findById(jobId)
        if (optionalJob.isEmpty) {
            throw ResourceNotFound(Job::class.simpleName!!, jobId.toString())
        }
        val job = optionalJob.get()

        val jobResults = results
            ?.map { resultData: Any? ->
                val jobResult = JobResult()
                var preparedForSavingResultData: String?
                var resultDataType: String? = null
                try {
                    preparedForSavingResultData = objectMapper.writeValueAsString(resultData)
                    //TODO  Тут кафка теряет тип объекта при пересылке объектов типа List<Object>. Всегда поолучается из json -> LinkedHashMap
                    resultDataType = resultData?.javaClass?.canonicalName
                } catch (e: JsonProcessingException) {
                    LOGGER.warn {
                        "Can not write result data to job=$jobId as JSON. Data will be saved as string. Data:\n$resultData"
                    }
                    preparedForSavingResultData = resultData.toString()
                }
                jobResult.data = preparedForSavingResultData
                jobResult.type = ObjectUtils.defaultIfNull(resultDataType, "text")
                jobResult
            }
            ?.toMutableList()

        job.status = newStatus
        val existingResults = job.results

        if (existingResults.isNullOrEmpty()) {
            job.results = jobResults
        } else {
            if (jobResults != null) {
                existingResults.addAll(jobResults)
            }
        }
        repository.save(job)
    }

    @Transactional
    override fun setErrors(jobId: UUID, errors: List<Any?>?) {
        val optionalJob = repository.findById(jobId)
        if (optionalJob.isEmpty) {
            throw ResourceNotFound(Job::class.java.name, jobId.toString())
        }
        val job = optionalJob.get()
        val jobResults = errors
            ?.filter { obj: Any? -> Objects.nonNull(obj) }
            ?.map { error: Any? ->
                val errorText = if (error is Throwable) error.message else error.toString()
                JobResult(UUID.randomUUID(), Instant.now(), errorText.toString(), "text")
            }
            ?.toMutableList()

        job.status = JobStatus.ERROR
        job.results = jobResults
        repository.save(job)
    }
}