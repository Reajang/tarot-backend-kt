package com.example.backend.service;

import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import java.util.List;

public interface TarotService {

    List<TarotCardDto> getAllCards();

    TarotResponse ask(TarotRequest request) throws Exception;

    TarotCardDto randomOne();
}
