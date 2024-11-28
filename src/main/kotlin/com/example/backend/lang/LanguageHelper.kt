package com.example.backend.lang

import com.example.backend.utils.LOGGER
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.ObjectUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.URI
import java.net.URISyntaxException
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

enum class Language(val code: String) {
    EN("en"),
    RU("ru")
}

@Component
class YandexTranslateHelper(
    val httpClient: HttpClient,
    val objectMapper: ObjectMapper
) {

    @Value("\${yandex.api.translate.url}")
    private lateinit var _url: String

    @Value("\${yandex.api.translate.auth.type}")
    private lateinit var _authType: String

    @Value("\${yandex.api.translate.auth.apikey}")
    private lateinit var _apiKey: String

    fun translate(textToTranslate: String?, sourceLanguage: Language, targetLanguage: Language): String {
        LOGGER.info {
            "Start translation via Yandex API test=${
                if (textToTranslate!!.length > 50) textToTranslate.substring(0, 50) + "..." else textToTranslate
            }\nfrom=${sourceLanguage.code}\nto=${targetLanguage.code}"
        }

        val httpRequest = prepareHttpRequest(textToTranslate!!, sourceLanguage, targetLanguage)

        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())

        val jsonNode = objectMapper.readTree(httpResponse.body())

        LOGGER.info { "Response from Yandex API: $jsonNode" }

        val firstTranslation = jsonNode["translations"][0]
        return deleteYandexSystemParentheses(firstTranslation["text"].asText())

    }

    @Throws(URISyntaxException::class)
    private fun prepareHttpRequest(
        textToTranslate: String,
        sourceLanguage: Language,
        targetLanguage: Language
    ): HttpRequest {
        return HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofByteArray(prepareBody(textToTranslate, sourceLanguage, targetLanguage)))
            .uri(URI(_url))
            .headers(
                "Content-Type", "application/json",
                "Authorization", _authType + StringUtils.SPACE + _apiKey
            )
            .build()
    }

    private fun prepareBody(textToTranslate: String, sourceLanguage: Language, targetLanguage: Language): ByteArray? {
        val objectNode = objectMapper.createObjectNode()
        val texts = arrayOf(ObjectUtils.defaultIfNull(textToTranslate, StringUtils.EMPTY))
        objectNode.put("sourceLanguageCode", sourceLanguage.code)
        objectNode.put("targetLanguageCode", targetLanguage.code)
        objectNode.put("texts", texts.contentToString())
        return objectNode.toString().toByteArray()
    }

    private fun deleteYandexSystemParentheses(translateResponseWithinParentheses: String): String {
        if (StringUtils.isBlank(translateResponseWithinParentheses)) {
            return StringUtils.EMPTY
        }
        var startIndex = 0
        var endIndex = translateResponseWithinParentheses.length
        var stringHadSystemParentheses = false
        if (translateResponseWithinParentheses[0] == '[') {
            startIndex++
            stringHadSystemParentheses = true
        }
        if (translateResponseWithinParentheses[translateResponseWithinParentheses.length - 1] == ']') {
            endIndex--
            stringHadSystemParentheses = true
        }
        return if (stringHadSystemParentheses) translateResponseWithinParentheses.substring(
            startIndex,
            endIndex
        ) else translateResponseWithinParentheses
    }
}