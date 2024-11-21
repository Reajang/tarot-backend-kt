package com.example.backend.events.tarot

import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.events.Event
import com.example.backend.events.EventType
import com.example.backend.lang.Language
import java.io.Serial
import java.util.*

class TarotPredictionResponseEvent(
    var cards: List<TarotCardDto?>?,
    var text: String?,
    var from: Language?,
    var to: Language?,
    var jobId: UUID
) : Event() {

    @Serial
    val serialVersionUID: Long = -3172818263712490758L

    override fun getEventType(): EventType {
        return EventType.TAROT_RESPONSE
    }
}