package com.example.backend.mapper

import com.example.backend.documents.TarotCard
import com.example.backend.dto.tarot.TarotCardDto
import com.example.backend.dto.tarot.TarotCardType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class TarotCardMapperTest {

    private var tarotCardMapper: TarotCardMapper = TarotCardMapperImpl()

    @Test
    fun mapToDtoTest() {
        val card = TarotCard(
            UUID.randomUUID(),
            TarotCardType("test", "test desc"),
            "test name",
            "test card desc",
            "test reversed desc",
            "test advice",
            UUID.randomUUID()
        )

        val result: TarotCardDto? = assertDoesNotThrow { tarotCardMapper.map(card, true) }

        Assertions.assertNotNull(result)
        assertEquals(card.id, result?.id)
        assertEquals(card.cardType?.name, result?.cardType?.name)
        assertEquals(card.cardType?.description, result?.cardType?.description)
        assertEquals(card.name, result?.name)
        assertEquals(card.description, result?.description)
        assertEquals(card.reversedDescription, result?.reversedDescription)
        assertEquals(card.advice, result?.advice)
        assertEquals(card.imageId, result?.imageId)
        assertEquals(true, result?.reversed)
    }
}