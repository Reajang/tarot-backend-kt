package com.example.backend.repository;

import com.example.backend.domain.Tost;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface TostRepository extends CrudRepository<Tost, UUID> {

    /**
     * Найти все для пользователя + общие
     *
     * @param userId id пользователя
     * @return что нашлось
     */
    List<Tost> findAllByUserIdOrCommonTrue(UUID userId);
}
