package com.example.backend.utils

import com.example.backend.documents.Job
import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import com.example.backend.gpt.ChatGPTService
import com.example.backend.helper.TarotHelper
import com.example.backend.lang.YandexTranslateHelper
import mu.KotlinLogging
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Instant
import java.util.*

val LANGUAGE_SERVICE_LOGGER: Logger = LoggerFactory.getLogger(YandexTranslateHelper::class.java)

val LOGGER = KotlinLogging.logger {}


fun newIdleJob(jobType: JobType?): Job {
    val newJob = Job()
    newJob.id = UUID.randomUUID()
    newJob.createDate = Instant.now()
    newJob.type = jobType
    newJob.status = JobStatus.IDLE
    return newJob
}
