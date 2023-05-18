package com.example.backend.repository;

import com.example.backend.domain.tarot.TarotCard;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarotCardRepository extends JpaRepository<TarotCard, UUID> {

}
