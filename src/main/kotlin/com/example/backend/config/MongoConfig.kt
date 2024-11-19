package com.example.backend.config

//import com.mongodb.reactivestreams.client.MongoClient
//import com.mongodb.reactivestreams.client.MongoClients
//import org.springframework.boot.CommandLineRunner
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
//import org.springframework.context.annotation.Bean
import io.mongock.runner.springboot.EnableMongock
import org.springframework.context.annotation.Configuration
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate

@Configuration
//@EnableMongoRepositories
@EnableMongock
class MongoConfig
//    : AbstractReactiveMongoConfiguration()
{


//    override fun getDatabaseName(): String {
//        return "tarot"
//    }
//
//    @Bean
//    override fun reactiveMongoClient(): MongoClient {
//        return MongoClients.create("mongodb://root:mongopw@localhost:27017")
//    }
//
//    @Bean
//    fun reactiveMongoTemplate(): ReactiveMongoTemplate {
//        return ReactiveMongoTemplate(reactiveMongoClient(), databaseName)
//    }

}