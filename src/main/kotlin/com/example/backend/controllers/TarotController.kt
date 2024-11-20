package com.example.backend.controllers

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.service.TarotService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/tarot")
class TarotController(private val tarotService: TarotService) {

    @GetMapping("/cards")
    fun allCards(): List<TarotCardDto> {
        return tarotService.getAllCards()
    }


    @PostMapping("/question")
    fun ask(@RequestBody request: TarotRequest?): TarotResponse? {
        return tarotService.ask(request)
    }

    /**
     * @return Async Job id
     */
    @PostMapping("/question/async")
    fun askAsync(@RequestBody request: TarotRequest?): UUID? {
        return tarotService.askAsync(request)
    }


    @GetMapping("/take-a-try")
    fun takeATry(): TarotCardDto {
        return tarotService.randomOne()
    }
}