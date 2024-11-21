package com.example.backend.dto.tarot

import java.util.*

data class TarotCardDto(
    var id: UUID? = null,
    var cardType: TarotCardType? = null,
    var name: String? = null,
    var description: String? = null,
    var reversedDescription: String? = null,
    var advice: String? = null,
    var reversed: Boolean? = false,
    var imageId: UUID? = null
)
