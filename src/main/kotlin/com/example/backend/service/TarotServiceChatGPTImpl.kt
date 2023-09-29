package com.example.backend.service

import com.example.backend.domain.system.JobType
import com.example.backend.domain.tarot.TarotCard
import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.events.publishers.TarotPublisher
import com.example.backend.helper.TarotHelper
import com.example.backend.mapper.TarotCardMapper
import com.example.backend.repository.TarotCardRepository
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.stream.Collectors

@Service
@AllArgsConstructor
class TarotServiceChatGPTImpl(
    val repository: TarotCardRepository,
    val jobService: JobService,
    val tarotHelper: TarotHelper,
    val tarotPublisher: TarotPublisher
) : TarotService {

    private val randomizer = Random(31)

    @Transactional(readOnly = true)
    override fun getAllCards(): List<TarotCardDto?>? {
        return repository.findAll()
            .stream()
            .map { card: TarotCard? ->
                TarotCardMapper.INSTANCE.map(
                    card,
                    randomizer.nextBoolean()
                )
            }
            .collect(Collectors.toList())
    }

    @Transactional
    override fun ask(request: TarotRequest?): TarotResponse? {
        return tarotHelper.futureTell(request!!)
    }

    override fun askAsync(request: TarotRequest?): UUID? {
        val jobId = jobService.createNew(JobType.TAROT_FUTURE_TELL)
        tarotPublisher.publishTarotPredictionRequestEvent(request, jobId)
        return jobId
    }

    @Transactional(readOnly = true)
    override fun randomOne(): TarotCardDto? {
        val tarotCards = repository.findAll()
        val cardIndex = randomizer.nextInt(tarotCards.size)
        val isCardReversed = randomizer.nextBoolean()
        return TarotCardMapper.INSTANCE.map(tarotCards[cardIndex], isCardReversed)

    }
}