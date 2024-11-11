package com.example.backend.config;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.mongodb.reactivestreams.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
@Configuration
//@EnableMongoRepositories
@EnableMongock
public class MongoConfig
//        extends AbstractReactiveMongoConfiguration
{
//
//    @Override
//    protected String getDatabaseName() {
//        return "tarot";
//    }
//
////    @Override
////    @Bean
////    public MongoClient reactiveMongoClient() {
////        return MongoClients.create("mongodb://root:mongopw@localhost:27017");
////    }
//
//    @Bean
//    public ReactiveMongoTemplate reactiveMongoTemplate() {
//        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
//    }
//
////    @Bean
////    @ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
////    public CommandLineRunner loadData(UserRepository repository) {
////        return (args) -> {
////            // save a couple of users
////            var users = Flux.just(
////                    new User("Gökhan", "1", 1),
////                    new User("Betül", "2", 12),
////                    new User("Zühtü", "3", 13)
////            );
////            repository.saveAll(users).subscribe();
////        };
////    }
}