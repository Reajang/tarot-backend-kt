package com.example.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.backend.domain.tarot.TarotCard;
import com.example.backend.dto.tarot.TarotCardDto;
import java.util.UUID;

import com.example.backend.dto.tarot.TarotCardType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TarotCardMapperTest {

    @Test
    void mapToDtoTest() {
        TarotCard card = new TarotCard(
            UUID.randomUUID(),
            new TarotCardType(UUID.randomUUID(), "test", "test desc"),
            "test name",
            "test card desc",
            "test reversed desc",
            "test advice",
            UUID.randomUUID()
        );

        TarotCardDto result = assertDoesNotThrow(() -> TarotCardMapper.INSTANCE.map(card, true));

        assertNotNull(result);
        assertEquals(card.getId(), result.id());
        assertEquals(card.getCardType().id(), result.cardType().id());
        assertEquals(card.getCardType().name(), result.cardType().name());
        assertEquals(card.getCardType().description(), result.cardType().description());
        assertEquals(card.getName(), result.name());
        assertEquals(card.getDescription(), result.description());
        assertEquals(card.getReversedDescription(), result.reversedDescription());
        assertEquals(card.getAdvice(), result.advice());
        assertEquals(card.getImageId(), result.imageId());
        assertTrue(result.reversed());
    }
}
