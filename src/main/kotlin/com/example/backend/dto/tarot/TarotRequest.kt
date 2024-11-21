package com.example.backend.dto.tarot

import com.example.backend.dto.common.Translatable
import com.example.backend.lang.Language

data class TarotRequest(
    var cards: List<TarotCardDto>? = null,
    var text: String? = null,
    var from: Language? = null,
    var to: Language? = null
) : Translatable {

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
