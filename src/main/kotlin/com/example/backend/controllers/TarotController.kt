package com.example.backend.controllers

import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.service.TarotService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/tarot")
class TarotController(
    private val tarotService: TarotService,
) {

    @GetMapping("/cards")
    fun allCards() = tarotService.getAllCards()


    @PostMapping("/question")
    fun ask(@RequestBody request: TarotRequest?): Mono<TarotResponse?> {
        return tarotService.ask(request)
    }

    /**
     * @return Async Job id
     */
    @PostMapping("/question/async")
    fun askAsync(@RequestBody request: TarotRequest?): Mono<UUID?> {
        return tarotService.askAsync(request)
    }

}