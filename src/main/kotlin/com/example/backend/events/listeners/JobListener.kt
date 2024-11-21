package com.example.backend.events.listeners

import com.example.backend.documents.JobStatus
import com.example.backend.events.Event
import com.example.backend.events.job.UpdateJobStatusEvent
import com.example.backend.service.JobService
import com.example.backend.utils.LOGGER
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = ["\${kafka.topic.job-events}"])
class JobListener(private val jobService: JobService) {

    @KafkaHandler
    fun receiveUpdateJobStatusEvent(@Payload event: UpdateJobStatusEvent) {
        LOGGER.info { "Received event $event" }
        if (event.results.isNullOrEmpty()) {
            jobService.update(event.jodId!!, event.newStatus)
        } else if (event.newStatus == JobStatus.ERROR) {
            jobService.setErrors(event.jodId!!, event.results)
        } else {
            jobService.update(event.jodId!!, event.newStatus, event.results)
        }
    }

    @KafkaHandler(isDefault = true)
    fun defaultHandler(@Payload event: Event?) {
        LOGGER.warn { "Received unknown event $event" }
    }
}