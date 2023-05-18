package com.example.backend.dto.tarot;

import java.util.UUID;

public record TarotCardDto(UUID id,
                           TarotCardTypeDto cardType,
                           String name,
                           String description,
                           String reversedDescription,
                           String advice,
                           boolean reversed,
                           UUID imageId) {
    public TarotCardDto {
    }

    public TarotCardDto(String name) {
        this(null, null, name, null, null, null, false, null);
    }
}
