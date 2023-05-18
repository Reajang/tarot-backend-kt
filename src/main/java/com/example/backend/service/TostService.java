package com.example.backend.service;

import com.example.backend.dto.tost.TostDto;
import java.util.List;
import java.util.UUID;

public interface TostService {

    List<TostDto> getList(UUID userId);

    TostDto create(UUID userId, String text);

    void update(UUID userId, UUID tostId, String text);
}
