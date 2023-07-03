package com.example.backend.events.listeners;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.dto.system.JobDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.Event;
import com.example.backend.events.publishers.JobPublisher;
import com.example.backend.events.publishers.TarotPublisher;
import com.example.backend.events.tarot.TarotPredictionRequestEvent;
import com.example.backend.helper.TarotHelper;
import com.example.backend.mapper.TarotCardMapper;
import com.example.backend.service.JobService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@KafkaListener(topics = "${kafka.topic.tarot-events}")
@Slf4j
public class TarotListener {

    private final TarotHelper tarotHelper;
    private final JobPublisher jobPublisher;
    private final JobService jobService;
    private final TarotPublisher tarotPublisher;

    @KafkaHandler
    public void receiveTarotPredictionRequestEvent(@Payload TarotPredictionRequestEvent event) {
        log.info("Received event {}", event);

        UUID jobId = event.getJobId();
        TarotRequest tarotRequest = TarotCardMapper.INSTANCE.map(event);
        log.info("Start async future telling for jobId={}. TarotRequest={}", jobId, tarotRequest);

        JobDto job = jobService.get(jobId);
        if (job == null) {
            log.error("The job (id={}) doesn't exist", jobId);
            return;
        }

        jobPublisher.publishUpdateJobStatusEvent(event.getJobId(), JobStatus.RUNNING);

        try {
            TarotResponse tarotResponse = tarotHelper.futureTell(tarotRequest);
            jobPublisher.publishUpdateJobStatusEvent(event.getJobId(), JobStatus.COMPLETE, List.of(tarotResponse));
//            tarotPublisher.publishTarotPredictionResponseEvent(tarotResponse, jobId);
        } catch (Exception e) {
            log.error("Error while async future telling for jobId={}, TarotRequest={}", jobId, tarotRequest);
            log.error("Error", e);
            jobPublisher.publishUpdateJobStatusEvent(event.getJobId(), JobStatus.ERROR, List.of(e));
        }
    }

//    @KafkaListener
//    public void receiveTarotPredictionResponseEvent(@Payload TarotPredictionResponseEvent event) {
//        log.info("Received event {}", event);
//    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Event event) {
        log.warn("Received unknown event {}", event);
    }
}
