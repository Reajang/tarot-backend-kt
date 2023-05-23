package com.example.backend.helpers;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.dto.system.JobDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.service.JobService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
@Slf4j
public class TarotHelper {

    private final ChatGPTHelper chatGPTHelper;
    private final YandexTranslateHelper yandexTranslateHelper;
    private final JobService jobService;


    @Async
    @Transactional
    public void futureTellAsync(TarotRequest request, UUID jobId) {
        log.info("Start async future telling in TarotHelper for jobId={}. TarotRequest={}", jobId, request);
        JobDto job = jobService.get(jobId);
        if (job == null) {
            log.error("The job (id={}) doesn't exist", jobId);
            return;
        }
        jobService.update(jobId, JobStatus.RUNNING);

        try {
            TarotResponse tarotResponse = futureTell(request);
            jobService.update(jobId, JobStatus.COMPLETE, List.of(tarotResponse));
        } catch (Exception e) {
            log.error("Error while async future telling for jobId={}, TarotRequest={}", jobId, request);
            log.error("Error", e);
            jobService.setErrors(jobId, List.of(e));
        }
    }

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
