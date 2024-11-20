package com.example.backend.repository;

import com.example.backend.documents.TarotCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TarotCardRepository extends MongoRepository<TarotCard, UUID>
{

}
