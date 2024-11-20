package com.example.backend.config

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka

@Configuration
@EnableKafka
@Slf4j
class KafkaConfig @Autowired constructor(val kafkaProperties: KafkaProperties) {
}