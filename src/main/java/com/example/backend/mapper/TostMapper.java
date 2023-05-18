package com.example.backend.mapper;

import com.example.backend.domain.tost.Tost;
import com.example.backend.dto.tost.TostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TostMapper {

    TostMapper INSTANCE = Mappers.getMapper(TostMapper.class);

    TostDto map(Tost tost);
}
