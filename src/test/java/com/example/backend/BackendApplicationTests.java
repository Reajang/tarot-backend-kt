package com.example.backend;

import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.testcontainers.KafkaTestContainer;
import com.example.backend.testcontainers.PostgresqlTestContainer;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Transactional
//@Testcontainers
@SpringBootTest
class BackendApplicationTests {

//    @Container
//    static PostgresqlTestContainer postgresqlTestContainer = new PostgresqlTestContainer();
//    @Container
//    static KafkaTestContainer kafkaTestContainer = new KafkaTestContainer();
//
//    static {
//        Startables.deepStart(
//            Stream.of(
//                postgresqlTestContainer,
//                kafkaTestContainer
//            )
//        );
//    }
//
//    @DynamicPropertySource
//    static void updatePropertiesAfterContextInit(DynamicPropertyRegistry registry) {
//        registry.add("test.container.postgresql.jdbc.url", postgresqlTestContainer::getJdbcUrl);
//        registry.add("test.container.kafka.bootstrap.servers", kafkaTestContainer::getBootstrapServers);
//    }

    @Test
    void contextLoads() {
    }


}
