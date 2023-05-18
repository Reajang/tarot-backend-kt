package com.example.backend.service;

import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import java.util.List;
import java.util.UUID;

public interface TarotService {

    List<TarotCardDto> getAllCards();

    TarotResponse ask(TarotRequest request) throws Exception;
    UUID askAsync(TarotRequest request) throws Exception;

    TarotCardDto randomOne();
}
