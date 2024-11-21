package com.example.backend.events

import java.io.Serializable
import java.time.Instant
import java.util.*

abstract class Event(
    val id: UUID = UUID.randomUUID(),
    val createDate: Instant = Instant.now(),
) : Serializable {

    abstract fun getEventType(): EventType
}