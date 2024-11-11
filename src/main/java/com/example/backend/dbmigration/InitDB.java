package com.example.backend.dbmigration;

import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.domain.tarot.TarotCardType;
import com.example.backend.repository.TarotCardRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@ChangeUnit(order = "001", id = "cards_init", author = "leonid")
public class InitDB {

    private final MongoTemplate mongoTemplate;

    @Execution
    public void execution() {
        mongoTemplate.insertAll(List.of(
                new TarotCard(
                        UUID.randomUUID(),
                        new TarotCardType(UUID.randomUUID(), "sdaf", "fgs"),
                        "qwe",
                        "sdfa",
                        "dsaf",
                        "djfal",
                        null
                ),
                new TarotCard(
                        UUID.randomUUID(),
                        new TarotCardType(UUID.randomUUID(), "4", "3"),
                        "45",
                        "df",
                        "5",
                        "3",
                        null
                )
        ));
    }

    @RollbackExecution
    public void rollback() {
    }
}
