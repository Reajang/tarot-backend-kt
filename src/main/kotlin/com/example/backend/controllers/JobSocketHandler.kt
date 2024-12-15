package com.example.backend.controllers

import com.example.backend.documents.Job
import com.example.backend.repos.JobRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Component
class JobSocketHandler(
    private val jobRepository: JobRepository,
    private val objectMapper: ObjectMapper
) : WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> {

        val responseOnInput = session.receive()
            .map { objectMapper.readValue(it.payloadAsText, Job::class.java) }
            .flatMap {
                it.id = UUID.randomUUID()
                jobRepository.save(it)
            }

        val defaultResponse = jobRepository.findAll()

        //The essential difference between merge and concat is that in merge, both streams are live.
        // In case of concat, first stream is terminated and then the other stream is concatenated to it
        val resultResponse: Flux<WebSocketMessage> = defaultResponse.concatWith(responseOnInput)
            .map { objectMapper.writeValueAsString(it) }
            .map { session.textMessage(it) }

        return session.send(resultResponse)
    }

}