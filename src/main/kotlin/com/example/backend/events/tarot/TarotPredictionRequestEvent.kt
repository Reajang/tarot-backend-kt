package com.example.backend.events.tarot

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.events.Event
import com.example.backend.events.EventType
import com.example.backend.lang.Language
import java.io.Serial
import java.util.*

class TarotPredictionRequestEvent(
    var jobId: UUID? = null,
    var cards: List<TarotCardDto?>? = null,
    var text: String? = null,
    var from: Language? = null,
    var to: Language? = null,
) : Event() {

    @Serial
    val serialVersionUID: Long = 8213057646751252853L

    override fun getEventType(): EventType {
        return EventType.TAROT_REQUEST
    }
}