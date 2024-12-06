package com.example.backend.controllers

import com.example.backend.documents.Job
import com.example.backend.documents.JobStatus
import com.example.backend.documents.JobType
import com.example.backend.repos.JobRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.time.Instant
import java.util.*

@Configuration
class JobRouter(
    private val jobRepository: JobRepository,
) {

    @Bean
    fun addNew(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            GET("/add"),
            { req ->
                ok()
                    .body(jobRepository.save(Job(UUID.randomUUID(), Instant.now(), Instant.now(), JobType.TEST, JobStatus.IDLE)), Job::class.java)
            }
        )
    }
}