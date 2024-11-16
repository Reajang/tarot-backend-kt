//package com.example.backend.service
//
//import com.example.backend.domain.system.JobType
//import com.example.backend.documents.tarot.TarotCard
//import com.example.backend.dto.tarot.TarotCardDto
//import com.example.backend.dto.tarot.TarotRequest
//import com.example.backend.dto.tarot.TarotResponse
//import com.example.backend.events.publishers.TarotPublisher
//import com.example.backend.helper.TarotHelper
//import com.example.backend.mapper.TarotCardMapper
//import com.example.backend.repository.CardReactiveRepository
//import lombok.AllArgsConstructor
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//import java.util.*
//import java.util.stream.Collectors
//
//@Service
//@AllArgsConstructor
//class TarotServiceChatGPTReactiveImpl(
//    val repository: CardReactiveRepository,
//    val jobService: JobService,
//    val tarotHelper: TarotHelper,
//    val tarotPublisher: TarotPublisher
//) : ReactiveTarotService {
//
//    private val randomizer = Random(31)
//
//    @Transactional(readOnly = true)
//    override fun getAllCards(): Flux<TarotCardDto> {
//        return repository.findAll()
//            .map { card: TarotCard? ->
//                TarotCardMapper.INSTANCE.map(
//                    card,
//                    randomizer.nextBoolean()
//                )
//            }
//    }
//
//    @Transactional
//    override fun ask(request: TarotRequest?): Mono<TarotResponse> {
//        return Mono.just(tarotHelper.futureTell(request!!))
//
//    }
//
//    override fun askAsync(request: TarotRequest?): Mono<UUID> {
//        val jobId = jobService.createNew(JobType.TAROT_FUTURE_TELL)
//        tarotPublisher.publishTarotPredictionRequestEvent(request, jobId)
//        return Mono.just(jobId)
//    }
//
//    @Transactional(readOnly = true)
//    override fun randomOne(): Mono<TarotCardDto> {
//        val tarotCards = repository.findAll()
//        val cardIndex = randomizer.nextInt(tarotCards.count().)
//        val isCardReversed = randomizer.nextBoolean()
//        return TarotCardMapper.INSTANCE.map(tarotCards[cardIndex], isCardReversed)
//
//    }
//}