package com.example.backend.dto.tarot

import com.example.backend.dto.common.Translatable
import com.example.backend.lang.Language

data class TarotResponse(
    var cards: List<TarotCardDto>? = null,
    var text: String? = null,
    var from: Language? = Language.EN,
    var to: Language? = Language.EN
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
