package com.example.backend.events;


import java.io.Serial;
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

    private String eventType;
    private String message;

}
