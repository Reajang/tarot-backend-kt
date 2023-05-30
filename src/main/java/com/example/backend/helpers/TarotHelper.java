package com.example.backend.helpers;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.publishers.TarotPublisher;
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
    private final TarotPublisher tarotPublisher;

    @Transactional
    public TarotResponse futureTell(TarotRequest request) throws Exception {
        TarotRequest translatedInEngRequest = translateRequestIfNecessary(request);
        TarotResponse tarotResponse = chatGPTHelper.tarotMeChatGPT(translatedInEngRequest);
        return translateResponseIfNecessary(request, tarotResponse);
    }

    private TarotRequest translateRequestIfNecessary(TarotRequest request)
        throws IOException, URISyntaxException, InterruptedException {
        if (request.from() != Language.EN) {
            String questionInEnglish = yandexTranslateHelper.translate(request.text(), request.from(), request.to());
            return new TarotRequest(request.cards(), questionInEnglish, request.from(), request.to());
        }
        return request;
    }

    private TarotResponse translateResponseIfNecessary(TarotRequest request, TarotResponse tarotResponse)
        throws IOException, URISyntaxException, InterruptedException {
        if (request.from() != Language.EN) {
            String responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text(), request.to(), request.from());
            return new TarotResponse(tarotResponse.cards(), responseInEnglish, request.to(), request.from());
        }
        return tarotResponse;
    }
}
