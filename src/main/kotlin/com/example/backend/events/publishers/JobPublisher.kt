package com.example.backend.events.publishers

import com.example.backend.documents.JobStatus
import com.example.backend.events.Event
import com.example.backend.events.job.UpdateJobStatusEvent
import com.example.backend.utils.LOGGER
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class JobPublisher(
    private val kafkaTemplate: KafkaTemplate<String, Event>
) {

    @Value("\${kafka.topic.job-events}")
    private var jobTopic: String? = null

    fun publishUpdateJobStatusEvent(jobId: UUID?, newStatus: JobStatus?) {
        LOGGER.info { "Publish UpdateJobStatusEvent. Set status $newStatus to job $jobId" }
        val event = UpdateJobStatusEvent(jobId, newStatus, emptyList())
        kafkaTemplate.send(jobTopic!!, event)
    }

    fun publishUpdateJobStatusEvent(jobId: UUID?, newStatus: JobStatus?, results: List<Any?>?) {
        LOGGER.info { "Publish UpdateJobStatusEvent. Set status $newStatus to job $jobId with results $results" }
        val event = UpdateJobStatusEvent(jobId, newStatus, results)
        kafkaTemplate.send(jobTopic!!, event)
    }
}