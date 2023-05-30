package com.example.backend.events;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = -6399274655831575396L;

    /**
     * Идентификатор сообщения
     */
    private final UUID id = UUID.randomUUID();

    /**
     * Дата и время создания сообщения
     */
    private final Instant createDate = Instant.now();

    public abstract String getEventType();
}
