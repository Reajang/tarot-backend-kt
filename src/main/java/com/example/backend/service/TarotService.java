package com.example.backend.service;

import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import java.util.List;

public interface TarotService {

    List<TarotCardDto> getAllCards();

    TarotResponse ask(TarotRequest request) throws Exception;

    TarotCardDto randomOne();
}
