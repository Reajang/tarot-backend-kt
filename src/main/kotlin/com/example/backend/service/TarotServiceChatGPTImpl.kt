package com.example.backend.service

import com.example.backend.documents.JobType
import com.example.backend.documents.TarotCard
import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.events.publishers.TarotPublisher
import com.example.backend.helper.TarotHelper
import com.example.backend.mapper.TarotCardMapper
import com.example.backend.repos.TarotCardRepository
import lombok.AllArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@AllArgsConstructor
class TarotServiceChatGPTImpl(
    private val repository: TarotCardRepository,
    private val jobService: JobService,
    private val tarotHelper: TarotHelper,
    private val tarotPublisher: TarotPublisher,
    private val tarotMapper: TarotCardMapper,
) : TarotService {

    private val randomizer = Random(31)

    @Transactional(readOnly = true)
    override fun getAllCards(): List<TarotCardDto?> {
        return repository.findAll()
            .map { card: TarotCard? ->
                tarotMapper.map(card, randomizer.nextBoolean())
            }
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
        // TODO there is no ability to updates cards. It's better so store it in the memory and do not call repository everytime
        val tarotCards = repository.findAll()
        val cardIndex = randomizer.nextInt(tarotCards.size)
        val isCardReversed = randomizer.nextBoolean()
        return tarotMapper.map(tarotCards[cardIndex], isCardReversed)

    }
}