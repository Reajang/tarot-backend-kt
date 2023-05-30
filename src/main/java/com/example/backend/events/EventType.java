package com.example.backend.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventType {

    TAROT_REQUEST("TAROT_REQUEST"),
    TAROT_RESPONSE("TAROT_RESPONSE"),
    UPDATE_JOB_STATUS("UPDATE_JOB_STATUS"),

    ;
    private final String description;
}
