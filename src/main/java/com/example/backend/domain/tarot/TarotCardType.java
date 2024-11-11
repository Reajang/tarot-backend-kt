package com.example.backend.domain.tarot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarotCardType {

    private UUID id;
    private String name;
    private String description;
}
