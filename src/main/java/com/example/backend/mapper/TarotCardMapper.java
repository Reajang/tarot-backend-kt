package com.example.backend.mapper;

import com.example.backend.documents.TarotCard;
import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.tarot.TarotPredictionRequestEvent;
import com.example.backend.events.tarot.TarotPredictionResponseEvent;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(
    builder = @Builder(disableBuilder = true)
)
public interface TarotCardMapper {

    TarotCardMapper INSTANCE = Mappers.getMapper(TarotCardMapper.class);

    @Mapping(target = "id", source = "domain.id")
    @Mapping(target = "cardType", source = "domain.cardType")
    @Mapping(target = "name", source = "domain.name")
    @Mapping(target = "description", source = "domain.description")
    @Mapping(target = "reversedDescription", source = "domain.reversedDescription")
    @Mapping(target = "advice", source = "domain.advice")
    @Mapping(target = "reversed", source = "reversed")
    @Mapping(target = "imageId", source = "domain.imageId")
    TarotCardDto map(TarotCard domain, boolean reversed);

    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    TarotPredictionResponseEvent map(TarotResponse tarotResponse);

    TarotResponse map(TarotPredictionResponseEvent tarotResponseEvent);

    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "eventType", ignore = true)
    TarotPredictionRequestEvent map(TarotRequest tarotRequest);

    TarotRequest map(TarotPredictionRequestEvent tarotRequestEvent);
}
