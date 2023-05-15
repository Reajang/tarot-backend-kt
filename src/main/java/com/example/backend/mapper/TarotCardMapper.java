package com.example.backend.mapper;

import com.example.backend.domain.TarotCard;
import com.example.backend.domain.TarotCardType;
import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotCardTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
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
}
