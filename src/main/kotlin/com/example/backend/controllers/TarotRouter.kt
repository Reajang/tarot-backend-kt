package com.example.backend.controllers

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.repos.TarotCardRepository
import com.example.backend.service.TarotService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok


@Configuration
class TarotRouter(
    private val tarotService: TarotService,
) {


//    @Bean
//    fun getEmployeeByIdRoute(): RouterFunction<ServerResponse> {
//        return RouterFunctions.route(
//            GET("/tarot/random"),
//            { req ->
//                ok()
//                    .body(tarotService.randomOne(), TarotCardDto::class.java)
//            }
//        )
//    }

}