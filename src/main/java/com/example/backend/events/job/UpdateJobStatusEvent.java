package com.example.backend.events.job;

import com.example.backend.domain.system.JobStatus;
import com.example.backend.events.Event;
import com.example.backend.events.EventType;
import java.io.Serial;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobStatusEvent extends Event {

    @Serial
    private static final long serialVersionUID = 1285378575504850018L;

    private UUID jodId;
    private JobStatus newStatus;
    private List<Object> results;

    private final String eventType = EventType.UPDATE_JOB_STATUS.getDescription();
}
