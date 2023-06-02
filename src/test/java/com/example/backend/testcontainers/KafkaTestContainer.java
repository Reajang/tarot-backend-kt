package com.example.backend.testcontainers;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaTestContainer extends KafkaContainer {

    public KafkaTestContainer() {
        super(DockerImageName.parse(
            "confluentinc/cp-kafka"
            )
//            .withTag("5.4.3")
//            .asCompatibleSubstituteFor("confluentinc/cp-kafka")
        );

        this.addEnv("KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR", "1");
        this.addEnv("KAFKA_TRANSACTION_STATE_LOG_MIN_ISR", "1");
    }

}

