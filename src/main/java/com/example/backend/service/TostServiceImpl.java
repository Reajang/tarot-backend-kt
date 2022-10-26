package com.example.backend.service;

import com.example.backend.domain.Tost;
import com.example.backend.dto.TostDto;
import com.example.backend.mapper.TostMapper;
import com.example.backend.repository.TostRepository;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TostServiceImpl implements TostService {

    private final TostRepository tostRepository;


    @Override
    @Transactional(readOnly = true)
    public List<TostDto> getList(UUID userId) {
        List<TostDto> result = tostRepository.findAllByUserIdOrCommonTrue(userId).stream()
            .map(TostMapper.INSTANCE::map)
            .collect(Collectors.toList());
        Collections.shuffle(result);
        return result;
    }

    @Override
    public TostDto create(UUID userId, String text) {
        Tost newTost = Tost.builder()
            .id(UUID.randomUUID())
            .userId(userId)
            .common(false)
            .text(text)
            .build();
        tostRepository.save(newTost);
        return TostMapper.INSTANCE.map(newTost);
    }

    @Override
    public void update(UUID userId, UUID tostId, String text) {
        tostRepository.findById(tostId).ifPresent(tost -> tost.setText(text));
    }
}
