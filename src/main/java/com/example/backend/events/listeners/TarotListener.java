package com.example.backend.events.listeners;

import com.example.backend.events.Event;
import com.example.backend.events.TarotPredictionRequestEvent;
import com.example.backend.events.TarotPredictionResponseEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@KafkaListener(topics = "${kafka.topic.tarot-events}")
@Slf4j
public class TarotListener {

    @KafkaHandler
    public void receiveTarotPredictionRequestEvent(@Payload TarotPredictionRequestEvent event) {
        log.info("Received event {}", event);
    }

    @KafkaHandler
    public void receiveTarotPredictionResponseEvent(@Payload TarotPredictionResponseEvent event) {
        log.info("Received event {}", event);
    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Event event) {
        log.warn("Received unknown event {}", event);
    }
}
