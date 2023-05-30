package com.example.backend.events.publishers;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.Event;
import com.example.backend.events.TarotPredictionRequestEvent;
import com.example.backend.events.TarotPredictionResponseEvent;
import com.example.backend.mapper.TarotCardMapper;
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

    public void publishTarotPredictionRequestEvent(TarotRequest tarotRequest) {
        log.info("Publish event {}", tarotRequest);
        TarotPredictionRequestEvent requestEvent = TarotCardMapper.INSTANCE.map(tarotRequest);
        kafkaTemplate.send(tarotTopic, requestEvent);
    }

    public void publishTarotPredictionResponseEvent(TarotResponse tarotResponse) {
        log.info("Publish event {}", tarotResponse);
        TarotPredictionResponseEvent responseEvent = TarotCardMapper.INSTANCE.map(tarotResponse);
        kafkaTemplate.send(tarotTopic, responseEvent);
    }
}
