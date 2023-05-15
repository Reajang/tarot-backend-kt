package com.example.backend.dto;

import java.util.List;

public record TarotRequest(List<TarotCardDto> cards, String question) {
}
