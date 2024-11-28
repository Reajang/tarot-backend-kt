package com.example.backend.dbmigration

import com.example.backend.documents.TarotCard
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.mongock.api.annotations.*
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.stereotype.Component

@Component
//@ChangeUnit(order = "001", id = "cards_init", author = "leonid")
class InitDB(
    private val mongoTemplate: ReactiveMongoTemplate,
    private val objectMapper: ObjectMapper,
    private val dbMigrationProps: DbMigrationProps,
    private val migrationHelper: MigrationHelper,
) {

    @BeforeExecution
    fun beforeExecution() {
        val recreateJsonMigrationInitFile = dbMigrationProps.recreateJsonMigrationInitFile
        if (recreateJsonMigrationInitFile == true) {
            migrationHelper.recreateJsonDbInitFileFromSqlFlyWayFile()
        }
    }

    @RollbackBeforeExecution
    fun rollbackBeforeExecution() {
    }

    @Execution
    fun execution() {
        val tarotCards = objectMapper.readValue(
            dbMigrationProps.initDbJsonFile?.file,
            object : TypeReference<List<TarotCard?>?>() {
            }
        )
        mongoTemplate.insertAll(tarotCards!!).subscribe()
    }

    @RollbackExecution
    fun rollback() {
    }
}