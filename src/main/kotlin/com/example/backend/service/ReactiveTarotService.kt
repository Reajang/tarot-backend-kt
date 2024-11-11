package com.example.backend.service

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface ReactiveTarotService {

    fun getAllCards(): Flux<TarotCardDto>

    fun ask(request: TarotRequest?): Mono<TarotResponse>

    fun askAsync(request: TarotRequest?): Mono<UUID>

    fun randomOne(): Mono<TarotCardDto>
}