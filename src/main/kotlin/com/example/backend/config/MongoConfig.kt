package com.example.backend.config

import com.example.backend.repos.JobRepository
import com.example.backend.repos.TarotCardRepository
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import io.mongock.api.config.MongockConfiguration
import io.mongock.driver.mongodb.reactive.driver.MongoReactiveDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories(
    basePackageClasses = [
        JobRepository::class,
        TarotCardRepository::class,
    ]
)
//@EnableMongock
class MongoConfig : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName() = "tarot"

    @Bean
    override fun reactiveMongoClient() = MongoClients.create()

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        return ReactiveMongoTemplate(reactiveMongoClient(), databaseName)
    }

    @Bean
    fun connectionDriver(
        @Value("\${spring.data.mongodb.database}") database: String,
        config: MongockConfiguration,
        client: MongoClient,
    ): MongoReactiveDriver {
        val driver = MongoReactiveDriver.withLockStrategy(
            client, database,
            config.lockAcquiredForMillis,
            config.lockQuitTryingAfterMillis,
            config.lockTryFrequencyMillis
        )
        return driver
    }
}