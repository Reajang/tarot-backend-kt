package com.example.backend.service

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import java.util.*

interface TarotService {

    fun getAllCards(): List<TarotCardDto?>?

    fun ask(request: TarotRequest?): TarotResponse?

    fun askAsync(request: TarotRequest?): UUID?

    fun randomOne(): TarotCardDto?
}