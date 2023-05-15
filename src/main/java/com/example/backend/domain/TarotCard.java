package com.example.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tarot_card")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TarotCard {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "card_type_id")
    private TarotCardType cardType;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "reversed_description")
    private String reversedDescription;

    @Column(name = "advice")
    private String advice;

    @Column(name = "image_id")
    private UUID imageId;
}
