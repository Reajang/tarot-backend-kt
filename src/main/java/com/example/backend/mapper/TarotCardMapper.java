package com.example.backend.mapper;

import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.domain.tarot.TarotCardType;
import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotCardTypeDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.events.TarotPredictionRequestEvent;
import com.example.backend.events.TarotPredictionResponseEvent;
import org.mapstruct.BeanMapping;
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

    TarotCardTypeDto mapType(TarotCardType type);

    @Mapping(target = "message", constant = "test tarot response event")
    @Mapping(target = "eventType", constant = "TAROT_RESPONSE")
    @Mapping(target = "objectId", ignore = true)
    TarotPredictionResponseEvent map(TarotResponse tarotResponse);

    @BeanMapping(ignoreByDefault = true)
    TarotResponse map(TarotPredictionResponseEvent tarotResponseEvent);

    @Mapping(target = "message", constant = "test tarot request event")
    @Mapping(target = "eventType", constant = "TAROT_REQUEST")
    @Mapping(target = "objectId", ignore = true)
    TarotPredictionRequestEvent map(TarotRequest tarotRequest);

    @BeanMapping(ignoreByDefault = true)
    TarotRequest map(TarotPredictionRequestEvent tarotRequestEvent);
}
