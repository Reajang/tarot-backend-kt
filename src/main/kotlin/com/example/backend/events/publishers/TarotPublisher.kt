package com.example.backend.events.publishers

import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.events.Event
import com.example.backend.mapper.TarotCardMapper
import com.example.backend.utils.LOGGER
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.*

@Component
class TarotPublisher(
    private val kafkaTemplate: KafkaTemplate<String, Event>,
) {
    @Value("\${kafka.topic.tarot-events}")
    private val tarotTopic: String? = null

    fun publishTarotPredictionRequestEvent(tarotRequest: TarotRequest?, jobId: UUID) {
        LOGGER.info { "Publish event $tarotRequest" }
        val requestEvent = TarotCardMapper.INSTANCE.map(tarotRequest)
        requestEvent.jobId = jobId
        kafkaTemplate.send(tarotTopic!!, requestEvent)
    }

    fun publishTarotPredictionResponseEvent(tarotResponse: TarotResponse?, jobId: UUID) {
        LOGGER.info { "Publish event $tarotResponse" }
        val responseEvent = TarotCardMapper.INSTANCE.map(tarotResponse)
        responseEvent.jobId = jobId
        kafkaTemplate.send(tarotTopic!!, responseEvent)
    }
}