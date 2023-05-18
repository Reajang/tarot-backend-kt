package com.example.backend.service;

import com.example.backend.domain.system.JobType;
import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.helpers.TarotHelper;
import com.example.backend.mapper.TarotCardMapper;
import com.example.backend.repository.TarotCardRepository;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TarotServiceChatGPTImpl implements TarotService {

    private final TarotCardRepository repository;
    private final JobService jobService;
    private final TarotHelper tarotHelper;

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
        return tarotHelper.futureTell(request);
    }

    @Override
    public UUID askAsync(TarotRequest request) {
        UUID jobId = jobService.createNew(JobType.TAROT_FUTURE_TELL);
        tarotHelper.futureTellAsync(request, jobId);
        return jobId;
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
