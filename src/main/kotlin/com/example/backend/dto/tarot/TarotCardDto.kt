package com.example.backend.dto.tarot

import java.util.*

data class TarotCardDto(
    var id: UUID?,
    var cardType: TarotCardType?,
    var name: String?,
    var description: String?,
    var reversedDescription: String?,
    var advice: String?,
    var reversed: Boolean? = false,
    var imageId: UUID?
) {
    constructor() : this(null, null, null, null, null, null, false, null)

}
