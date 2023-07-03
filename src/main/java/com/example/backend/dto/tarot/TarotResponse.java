package com.example.backend.dto.tarot;

import com.example.backend.dto.common.Translatable;
import com.example.backend.lang.Language;
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
