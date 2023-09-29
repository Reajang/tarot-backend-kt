package com.example.backend.utils

import com.example.backend.gpt.ChatGPTService
import com.example.backend.helper.TarotHelper
import com.example.backend.lang.YandexTranslateHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val CHAT_GPT_SERVICE_LOGGER: Logger = LoggerFactory.getLogger(ChatGPTService::class.java)
val LANGUAGE_SERVICE_LOGGER: Logger = LoggerFactory.getLogger(YandexTranslateHelper::class.java)
val TAROT_SERVICE_LOGGER: Logger = LoggerFactory.getLogger(TarotHelper::class.java)
