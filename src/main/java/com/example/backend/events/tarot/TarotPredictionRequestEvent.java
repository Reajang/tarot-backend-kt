package com.example.backend.events.tarot;

import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.events.Event;
import com.example.backend.events.EventType;
import com.example.backend.helpers.Language;
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
public class TarotPredictionRequestEvent extends Event {

    @Serial
    private static final long serialVersionUID = 8213057646751252853L;

    private final String eventType = EventType.TAROT_REQUEST.getDescription();

    private UUID jobId;

    private List<TarotCardDto> cards;
    private String text;
    private Language from;
    private Language to;
}
