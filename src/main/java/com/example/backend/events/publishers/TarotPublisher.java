package com.example.backend.events.publishers;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.Event;
import com.example.backend.events.tarot.TarotPredictionRequestEvent;
import com.example.backend.events.tarot.TarotPredictionResponseEvent;
import com.example.backend.mapper.TarotCardMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TarotPublisher {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Value("${kafka.topic.tarot-events}")
    private String tarotTopic;

    public void publishTarotPredictionRequestEvent(TarotRequest tarotRequest, UUID jobId) {
        log.info("Publish event {}", tarotRequest);
        TarotPredictionRequestEvent requestEvent = TarotCardMapper.INSTANCE.map(tarotRequest);
        requestEvent.setJobId(jobId);
        kafkaTemplate.send(tarotTopic, requestEvent);
    }

    public void publishTarotPredictionResponseEvent(TarotResponse tarotResponse, UUID jobId) {
        log.info("Publish event {}", tarotResponse);
        TarotPredictionResponseEvent responseEvent = TarotCardMapper.INSTANCE.map(tarotResponse);
        responseEvent.setJobId(jobId);
        kafkaTemplate.send(tarotTopic, responseEvent);
    }
}
