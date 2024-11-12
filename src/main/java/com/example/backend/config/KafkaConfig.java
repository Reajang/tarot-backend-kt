package com.example.backend.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
@Slf4j
@AllArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;
}