package com.example.backend.helpers;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.gpt.ChatGPTService;
import com.example.backend.lang.Language;
import com.example.backend.lang.YandexTranslateHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class TarotHelper {

    private final ChatGPTService chatGPTHelper;
    private final YandexTranslateHelper yandexTranslateHelper;

    @Transactional
    public TarotResponse futureTell(TarotRequest request) {
        log.info("Try get prediction");
        TarotRequest translatedInEngRequest = translateRequestIfNecessary(request);
        TarotResponse tarotResponse = chatGPTHelper.tarotMeChatGPT(translatedInEngRequest);
        log.info("Successfully got tarot prediction");
        return translateResponseIfNecessary(request, tarotResponse);
    }

    private TarotRequest translateRequestIfNecessary(TarotRequest request) {
        if (request.from() != Language.EN) {
            log.info("Tarot request should be translated from={} to{}", request.from(), request.to());
            String questionInEnglish = yandexTranslateHelper.translate(request.text(), request.from(), request.to());
            return new TarotRequest(request.cards(), questionInEnglish, request.from(), request.to());
        }
        return request;
    }

    private TarotResponse translateResponseIfNecessary(TarotRequest request, TarotResponse tarotResponse) {
        if (request.from() != Language.EN) {
            log.info("Tarot response should be translated back from={} to{}", request.to(), request.from());
            String responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text(), request.to(), request.from());
            return new TarotResponse(tarotResponse.cards(), responseInEnglish, request.to(), request.from());
        }
        return tarotResponse;
    }
}
