package com.example.backend.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "tost")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tost {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "text")
    private String text;
    @Column(name = "common")
    private boolean common;
}
