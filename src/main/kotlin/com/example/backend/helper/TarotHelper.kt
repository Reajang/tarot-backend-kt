package com.example.backend.helper

import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.gpt.ChatGPTService
import com.example.backend.lang.Language
import com.example.backend.lang.YandexTranslateHelper
import com.example.backend.utils.LOGGER
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TarotHelper(
    val chatGPTService: ChatGPTService,
    val yandexTranslateHelper: YandexTranslateHelper
) {

    @Transactional
    fun futureTell(request: TarotRequest): TarotResponse {
        LOGGER.info { "Try get prediction" }
        val translatedInEngRequest = translateRequestIfNecessary(request)
        val tarotResponse: TarotResponse = chatGPTService.tarotMeChatGPT(translatedInEngRequest)
        LOGGER.info { "Successfully got tarot prediction" }
        val translatedResponse = translateResponseIfNecessary(request, tarotResponse)
        return translatedResponse
    }

    private fun translateRequestIfNecessary(request: TarotRequest): TarotRequest {
        if (request.from != Language.EN) {
            LOGGER.info { "Tarot request should be translated from=${request.from} to=${request.to}" }
            val questionInEnglish = yandexTranslateHelper.translate(request.text, request.from!!, request.to!!)
            return TarotRequest(request.cards, questionInEnglish, request.from, request.to)
        }
        return request
    }

    private fun translateResponseIfNecessary(request: TarotRequest, tarotResponse: TarotResponse): TarotResponse {
        if (request.from != Language.EN) {
            LOGGER.info { "Tarot response should be translated back from=${request.to} to=${request.from}" }
            val responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text, request.to!!, request.from!!)
            return TarotResponse(tarotResponse.cards, responseInEnglish, request.to, request.from)
        }
        return tarotResponse
    }
}