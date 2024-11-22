package com.example.backend.mapper

import com.example.backend.documents.TarotCard
import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotRequest
import com.example.backend.dto.tarot.TarotResponse
import com.example.backend.events.tarot.TarotPredictionRequestEvent
import com.example.backend.events.tarot.TarotPredictionResponseEvent
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper
interface TarotCardMapper {

    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "cardType", source = "domain.cardType")
    @Mapping(target = "name", source = "domain.name")
    @Mapping(target = "description", source = "domain.description")
    @Mapping(target = "reversedDescription", source = "domain.reversedDescription")
    @Mapping(target = "advice", source = "domain.advice")
    @Mapping(target = "reversed", source = "reversed")
    @Mapping(target = "imageId", source = "domain.imageId")
    fun map(domain: TarotCard?, reversed: Boolean): TarotCardDto?

    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    fun map(tarotResponse: TarotResponse?): TarotPredictionResponseEvent?

    fun map(tarotResponseEvent: TarotPredictionResponseEvent?): TarotResponse?

    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    fun map(tarotRequest: TarotRequest?): TarotPredictionRequestEvent?

    fun map(tarotRequestEvent: TarotPredictionRequestEvent?): TarotRequest?
}