package com.example.backend.dbmigration;

import com.example.backend.documents.TarotCard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mongock.api.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@ChangeUnit(order = "001", id = "cards_init", author = "leonid")
public class InitDB {

    private final MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper;
    private final DbMigrationProps dbMigrationProps;
    private final MigrationHelper migrationHelper;

    @BeforeExecution
    public void beforeExecution() throws IOException {
        Boolean recreateJsonMigrationInitFile = dbMigrationProps.getRecreateJsonMigrationInitFile();
        if (recreateJsonMigrationInitFile) {
            migrationHelper.recreateJsonDbInitFileFromSqlFlyWayFile();
        }
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() throws IOException {  }

    @Execution
    public void execution() throws IOException {
        List<TarotCard> tarotCards = objectMapper.readValue(
                dbMigrationProps.getInitDbJsonFile().getFile(),
                new TypeReference<List<TarotCard>>() {
                }
        );
        mongoTemplate.insertAll(tarotCards);
    }

    @RollbackExecution
    public void rollback() {
    }
}
