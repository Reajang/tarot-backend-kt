package com.example.backend.events.listeners;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.events.Event;
import com.example.backend.events.job.UpdateJobStatusEvent;
import com.example.backend.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@KafkaListener(topics = "${kafka.topic.job-events}")
@Slf4j
public class JobListener {

    private final JobService jobService;

    @KafkaHandler
    public void receiveUpdateJobStatusEvent(@Payload UpdateJobStatusEvent event) {
        log.info("Received event {}", event);
        if (CollectionUtils.isEmpty(event.getResults())) {
            jobService.update(event.getJodId(), event.getNewStatus());
        } else if (event.getNewStatus() == JobStatus.ERROR) {
            jobService.setErrors(event.getJodId(), event.getResults());
        } else {
            jobService.update(event.getJodId(), event.getNewStatus(), event.getResults());
        }
    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Event event) {
        log.warn("Received unknown event {}", event);
    }
}
