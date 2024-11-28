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
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
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
    override fun getAllCards(): Flux<TarotCardDto?> {
        return repository.findAll()
            .map { card: TarotCard? ->
                tarotMapper.map(card, randomizer.nextBoolean())
            }
    }

    @Transactional
    override fun ask(request: TarotRequest?): Mono<TarotResponse?> {
        return Mono.just(tarotHelper.futureTell(request!!))
    }

    @Transactional
    override fun askAsync(request: TarotRequest?): Mono<UUID?> {
        return jobService.createNew(JobType.TAROT_FUTURE_TELL)
            .map { it?.id }
            .doOnNext { tarotPublisher.publishTarotPredictionRequestEvent(request, it!!) }
    }
}