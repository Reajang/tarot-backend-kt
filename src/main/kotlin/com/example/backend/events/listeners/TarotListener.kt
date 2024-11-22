package com.example.backend.events.listeners

import com.example.backend.documents.JobStatus
import com.example.backend.events.Event
import com.example.backend.events.publishers.JobPublisher
import com.example.backend.events.publishers.TarotPublisher
import com.example.backend.events.tarot.TarotPredictionRequestEvent
import com.example.backend.helper.TarotHelper
import com.example.backend.mapper.TarotCardMapper
import com.example.backend.service.JobService
import com.example.backend.utils.LOGGER
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
@KafkaListener(topics = ["\${kafka.topic.tarot-events}"])
class TarotListener(
    private val tarotHelper: TarotHelper,
    private val jobPublisher: JobPublisher,
    private val jobService: JobService,
    private val tarotPublisher: TarotPublisher,
    private val tarotCardMapper: TarotCardMapper,
) {

    @KafkaHandler
    fun receiveTarotPredictionRequestEvent(@Payload event: TarotPredictionRequestEvent) {
        LOGGER.info { "Received event $event" }

        val jobId = event.jobId
        val tarotRequest = tarotCardMapper.map(event)
        LOGGER.info { "Start async future telling for jobId=$jobId. TarotRequest=$tarotRequest" }

        val job = jobService.get(jobId!!)
        if (job == null) {
            LOGGER.error { "The job (id=$jobId) doesn't exist" }
            return
        }

        jobPublisher.publishUpdateJobStatusEvent(event.jobId, JobStatus.RUNNING)

        try {
            val tarotResponse = tarotHelper.futureTell(tarotRequest!!)
            jobPublisher.publishUpdateJobStatusEvent(event.jobId, JobStatus.COMPLETE, listOf(tarotResponse))
            //            tarotPublisher.publishTarotPredictionResponseEvent(tarotResponse, jobId);
        } catch (e: Exception) {
            LOGGER.error { "Error while async future telling for jobId=$jobId, TarotRequest=$tarotRequest" }
            LOGGER.error { "Error: $e" }
            jobPublisher.publishUpdateJobStatusEvent(event.jobId, JobStatus.ERROR, listOf(e))
        }
    }


    //    @KafkaListener
    //    public void receiveTarotPredictionResponseEvent(@Payload TarotPredictionResponseEvent event) {
    //        log.info("Received event {}", event);
    //    }
    @KafkaHandler(isDefault = true)
    fun defaultHandler(@Payload event: Event?) {
        LOGGER.warn { "Received unknown event $event" }
    }
}