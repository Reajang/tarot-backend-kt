package com.example.backend.dto.tarot;

import com.example.backend.dto.common.Translatable;
import com.example.backend.lang.Language;
import java.util.List;

public record TarotRequest(
    List<TarotCardDto> cards,
    String text,
    Language from,
    Language to) implements Translatable {
}
