package com.example.backend.aop;

import com.example.backend.helpers.YandexTranslateHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class TranslatorAspect {

    private final YandexTranslateHelper yandexTranslateHelper;

//    @Around("execution(public com.example.backend.dto.common.Translatable *(com.example.backend.dto.common.Translatable))")
//    public void logAroundAllMethods(ProceedingJoinPoint joinPoint) throws IOException, URISyntaxException, InterruptedException {
//       log.info("");
//        Translatable request =  (Translatable) joinPoint.getArgs()[0];
//        TarotRequest translatedInEngRequest;
//        if (request.from() != Language.EN) {
//            String questionInEnglish = yandexTranslateHelper.translate(request.text(), request.from(), request.to());
//            translatedInEngRequest = new TarotRequest(request.cards(), questionInEnglish, request.from(), request.to());
//        } else {
//            translatedInEngRequest = request;
//        }
//
//        TarotResponse tarotResponse = chatGPTHelper.tarotMeChatGPT(translatedInEngRequest);
//
//        if (request.from() != Language.EN) {
//            String responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text(), request.to(), request.from());
//            return new TarotResponse(tarotResponse.cards(), responseInEnglish, request.to(), request.from());
//        }
//    }
}
