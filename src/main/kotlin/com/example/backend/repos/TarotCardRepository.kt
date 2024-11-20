package com.example.backend.repos

import com.example.backend.documents.TarotCard
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TarotCardRepository : MongoRepository<TarotCard, UUID> {
}