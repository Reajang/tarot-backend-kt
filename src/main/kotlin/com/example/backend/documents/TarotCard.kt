package com.example.backend.documents

import com.example.backend.dto.tarot.TarotCardType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class TarotCard(
    @Id
    var id: UUID? = null,
    var cardType: TarotCardType? = null,
    var name: String? = null,
    var description: String? = null,
    var reversedDescription: String? = null,
    var advice: String? = null,
    var imageId: UUID? = null,
)