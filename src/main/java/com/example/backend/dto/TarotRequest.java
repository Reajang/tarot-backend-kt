package com.example.backend.dto;

import com.example.backend.helpers.Language;
import java.util.List;

public record TarotRequest(
    List<TarotCardDto> cards,
    String text,
    Language from,
    Language to) implements Translatable {
}
