package com.example.backend.domain.tarot;

import com.example.backend.dto.tarot.TarotCardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarotCard {

    @Id
    private UUID id;

    private TarotCardType cardType;

    private String name;

    private String description;

    private String reversedDescription;

    private String advice;

    private UUID imageId;
}
