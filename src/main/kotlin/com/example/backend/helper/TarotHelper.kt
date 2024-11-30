package com.example.backend.helper

import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.gpt.ChatGPTService
import com.example.backend.lang.Language
import com.example.backend.lang.YandexTranslateHelper
import com.example.backend.utils.LOGGER
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TarotHelper(
    val chatGPTService: ChatGPTService,
    val yandexTranslateHelper: YandexTranslateHelper
) {

    fun futureTell(request: TarotRequest): Mono<TarotResponse?> {
        LOGGER.info { "Try get prediction" }
        val translatedInEngRequest = translateRequestIfNecessary(request)
        val tarotResponse: Mono<TarotResponse?> = chatGPTService.tarotMeChatGPT(translatedInEngRequest)
        val translatedResponse = translateResponseIfNecessary(request, tarotResponse)
        return translatedResponse
    }

    private fun translateResponseIfNecessary(
        request: TarotRequest,
        tarotResponse: Mono<TarotResponse?>
    ): Mono<TarotResponse?> {
        if (request.from != Language.EN) {
            LOGGER.info { "Tarot response should be translated back from=${request.to} to=${request.from}" }

            return tarotResponse.flatMap { response ->
                yandexTranslateHelper.translate(response?.text!!, request.to!!, request.from!!)
                    .map { translatedText ->
                        TarotResponse(response.cards, translatedText)
                    }
            }
        }
        return tarotResponse
    }

    private fun translateRequestIfNecessary(request: TarotRequest): Mono<TarotRequest> {
        if (request.from != Language.EN) {
            LOGGER.info { "Tarot request should be translated from=${request.from} to=${request.to}" }
            return yandexTranslateHelper.translate(request.text!!, request.from!!, request.to!!)
                .map { translatedQuestion ->
                    TarotRequest(request.cards, translatedQuestion, request.from, request.to)
                }
        }
        return Mono.just(request)
    }
}