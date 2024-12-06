package com.example.backend.controllers

import com.example.backend.documents.Job
import com.example.backend.repos.JobRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class JobSocketHandler(
    private val jobRepository: JobRepository,
    private val objectMapper: ObjectMapper
) : WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> {

        val input = session.receive()
            .map {objectMapper.readValue(it.payloadAsText, Job::class.java)}
            .doOnNext{
                var newJob: Job = it
                newJob.id = null
                jobRepository.save(newJob)
            }
            .then()

        val response = jobRepository.findAll()
            .map { objectMapper.writeValueAsString(it) }


        val output = session.send(response.map(session::textMessage))

        return Mono.zip(input, output).then()
    }

}