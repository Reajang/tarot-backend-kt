package com.example.backend.dto.tarot

import com.example.backend.dto.common.Translatable
import com.example.backend.lang.Language

data class TarotRequest(
    var cards: List<TarotCardDto>?,
    var text: String?,
    var from: Language?,
    var to: Language?
) : Translatable {

    constructor() : this(null, null, null, null)

    override fun from(): Language? {
        return from
    }

    override fun to(): Language? {
        return to
    }

    override fun text(): String? {
        return text
    }
}
