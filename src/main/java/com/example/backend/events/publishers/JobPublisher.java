package com.example.backend.events.publishers;

import com.example.backend.documents.JobStatus;
import com.example.backend.events.Event;
import com.example.backend.events.job.UpdateJobStatusEvent;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobPublisher {

    @Value("${kafka.topic.job-events}")
    private String jobTopic;

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void publishUpdateJobStatusEvent(UUID jobId, JobStatus newStatus) {
        log.info("Publish UpdateJobStatusEvent. Set status {} to job {}", newStatus, jobId);
        UpdateJobStatusEvent event = new UpdateJobStatusEvent(jobId, newStatus, Collections.emptyList());
        kafkaTemplate.send(jobTopic, event);
    }

    public void publishUpdateJobStatusEvent(UUID jobId, JobStatus newStatus, List<Object> results) {
        log.info("Publish UpdateJobStatusEvent. Set status {} to job {} with results {}", newStatus, jobId, results);
        UpdateJobStatusEvent event = new UpdateJobStatusEvent(jobId, newStatus, results);
        kafkaTemplate.send(jobTopic, event);
    }

}
