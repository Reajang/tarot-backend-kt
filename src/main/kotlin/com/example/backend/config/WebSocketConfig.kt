package com.example.backend.config

import com.example.backend.controllers.JobSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.socket.config.annotation.EnableWebSocket

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val jobSocketHandler: JobSocketHandler,
) {

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/ws" to jobSocketHandler)
        val order = -1 // before annotated controllers

        return SimpleUrlHandlerMapping(map, order)
    }


}