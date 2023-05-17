package com.example.backend.dto;

import com.example.backend.helpers.Language;
import java.util.List;

public record TarotResponse(
    List<TarotCardDto> cards,
    String text,
    Language from,
    Language to) implements Translatable {


    public TarotResponse {
    }

    public TarotResponse(List<TarotCardDto> cards, String text) {
        this(cards, text, Language.EN, Language.EN);
    }
}
