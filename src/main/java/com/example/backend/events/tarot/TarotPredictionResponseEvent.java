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
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarotPredictionResponseEvent extends Event {

    @Serial
    private static final long serialVersionUID = -3172818263712490758L;

    private final String eventType = EventType.TAROT_RESPONSE.getDescription();

    private List<TarotCardDto> cards;
    private String text;
    private Language from;
    private Language to;
    private UUID jobId;

}
