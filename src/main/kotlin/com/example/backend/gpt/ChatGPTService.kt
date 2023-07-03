package com.example.backend.gpt

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.utils.LOGGER
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*


val SPECIAL_SYMBOLS_TO_EXCLUDE_REGEX: Regex = Regex("\n")
const val TAROT_DEFAULT_TEMPLATE = "Hi, GPT. Tell me the TAROT. My question is - \"%s\". My cards are \"%s\""
const val TAROT_DEFAULT_TEMPLATE_LONG = "Hi, GPT. Tell me the TAROT." +
        " My question is - \"%s\"." +
        " My cards are \"%s\"." +
        " Conditions: " +
        "1. Make a prediction for 2000 characters."

@Component
class ChatGPTService(
    val httpClient: HttpClient,
    val objectMapper: ObjectMapper
) {

    @Value("\${gpt.api.url}")
    private lateinit var _url: String

    @Value("\${gpt.api.auth.type}")
    private lateinit var _authType: String

    @Value("\${gpt.api.auth.bearer}")
    private lateinit var _bearer: String

    @Value("\${gpt.api.model}")
    private lateinit var _model: String

    @Value("\${gpt.api.max_tokens}")
    private lateinit var _maxTokens: String

    @Value("\${gpt.api.temperature}")
    private lateinit var _temperature: String

    fun tarotMeChatGPT(request: TarotRequest): TarotResponse {
        LOGGER.info("Send tarot request to ChatGPT")
        val httpRequest = prepareHttpRequest(request)
        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
        LOGGER.info("Waiting for responses...")

        //Бывает клиент возвращает ошибки
        val responseBody = objectMapper.readTree(httpResponse.body())
        LOGGER.info("ChatGPT response: {}", responseBody)
        LOGGER.info("Try to find choices.message.content in response")

        val gptResponse = responseBody["choices"].asSequence()
            .filter { obj: JsonNode? ->
                Objects.nonNull(
                    obj
                )
            }
            //            .map(jsonNode -> jsonNode.get("text").asText())
            .map { jsonNode: JsonNode ->
                jsonNode["message"]["content"].asText()
            }
            .filter { cs: String? -> StringUtils.isNotBlank(cs) }
            .map { answer: String ->
                answer.replace(
                    SPECIAL_SYMBOLS_TO_EXCLUDE_REGEX,
                    StringUtils.EMPTY
                )
            }
            .joinToString("\n")
        return TarotResponse(request.cards, gptResponse)
    }

    private fun prepareHttpRequest(request: TarotRequest): HttpRequest {
        return HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofByteArray(prepareBody(request)))
            .uri(URI(_url))
            .headers(
                "Content-Type", "application/json",
                "Authorization", _authType + StringUtils.SPACE + _bearer
            )
            .build()
    }

    private fun prepareBody(request: TarotRequest): ByteArray {
        val objectNode = objectMapper.createObjectNode()
        objectNode.put("model", _model)
        objectNode.put("max_tokens", _maxTokens.toLong())
        objectNode.put("temperature", _temperature.toDouble())
        //        objectNode.put("prompt", prepareQuestion(request));
        val messagesArrayNode = objectNode.putArray("messages")
        val messagesNode = messagesArrayNode.insertObject(0)
        messagesNode.put("role", "user")
        messagesNode.put("content", prepareQuestion(request))
        return objectNode.toString().toByteArray()
    }

    private fun prepareQuestion(request: TarotRequest): String {
        val cards = request.cards.asSequence()
            .map { card: TarotCardDto -> if (card.reversed) card.name + " (reversed)" else card.name }
            .joinToString(", ")
        return String.format(TAROT_DEFAULT_TEMPLATE_LONG, request.text, cards)
    }
}


