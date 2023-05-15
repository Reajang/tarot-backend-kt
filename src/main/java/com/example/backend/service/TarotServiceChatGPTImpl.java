package com.example.backend.service;

import com.example.backend.domain.TarotCard;
import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import com.example.backend.helpers.ChatGPTHelper;
import com.example.backend.mapper.TarotCardMapper;
import com.example.backend.repository.TarotCardRepository;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TarotServiceChatGPTImpl implements TarotService {

    private final TarotCardRepository repository;
    private final ChatGPTHelper chatGPTHelper;

    private final Random randomizer = new Random(31);

    @Override
    public List<TarotCardDto> getAllCards() {
        return repository.findAll()
            .stream()
            .map(card -> TarotCardMapper.INSTANCE.map(card, randomizer.nextBoolean()))
            .collect(Collectors.toList());

    }

    @Override
    public TarotResponse ask(TarotRequest request) throws Exception {
        return chatGPTHelper.tarotMeChatGPT(request);
    }

    @Override
    public TarotCardDto randomOne() {
        List<TarotCard> tarotCards = repository.findAll();
        Random random = new Random(31);
        int cardIndex = random.nextInt(tarotCards.size());
        boolean isCardReversed = random.nextBoolean();
        return TarotCardMapper.INSTANCE.map(tarotCards.get(cardIndex), isCardReversed);
    }
}
