package com.example.backend.helpers;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class TarotHelper {

    private final ChatGPTHelper chatGPTHelper;
    private final YandexTranslateHelper yandexTranslateHelper;

    @Transactional
    public TarotResponse futureTell(TarotRequest request) throws Exception {
        log.info("Try get prediction");
        TarotRequest translatedInEngRequest = translateRequestIfNecessary(request);
        TarotResponse tarotResponse = chatGPTHelper.tarotMeChatGPT(translatedInEngRequest);
        log.info("Successfully got tarot prediction");
        return translateResponseIfNecessary(request, tarotResponse);
    }

    private TarotRequest translateRequestIfNecessary(TarotRequest request)
        throws IOException, URISyntaxException, InterruptedException {
        if (request.from() != Language.EN) {
            log.info("Tarot request should be translated from={} to{}", request.from(), request.to());
            String questionInEnglish = yandexTranslateHelper.translate(request.text(), request.from(), request.to());
            return new TarotRequest(request.cards(), questionInEnglish, request.from(), request.to());
        }
        return request;
    }

    private TarotResponse translateResponseIfNecessary(TarotRequest request, TarotResponse tarotResponse)
        throws IOException, URISyntaxException, InterruptedException {
        if (request.from() != Language.EN) {
            log.info("Tarot response should be translated back from={} to{}", request.to(), request.from());
            String responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text(), request.to(), request.from());
            return new TarotResponse(tarotResponse.cards(), responseInEnglish, request.to(), request.from());
        }
        return tarotResponse;
    }
}
