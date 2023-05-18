package com.example.backend.service;

import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.helpers.ChatGPTHelper;
import com.example.backend.helpers.Language;
import com.example.backend.helpers.YandexTranslateHelper;
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
    private final YandexTranslateHelper yandexTranslateHelper;

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
        TarotRequest translatedInEngRequest;
        if (request.from() != Language.EN) {
            String questionInEnglish = yandexTranslateHelper.translate(request.text(), request.from(), request.to());
            translatedInEngRequest = new TarotRequest(request.cards(), questionInEnglish, request.from(), request.to());
        } else {
            translatedInEngRequest = request;
        }

        TarotResponse tarotResponse = chatGPTHelper.tarotMeChatGPT(translatedInEngRequest);

        if (request.from() != Language.EN) {
            String responseInEnglish = yandexTranslateHelper.translate(tarotResponse.text(), request.to(), request.from());
            return new TarotResponse(tarotResponse.cards(), responseInEnglish, request.to(), request.from());
        }
        return tarotResponse;
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
