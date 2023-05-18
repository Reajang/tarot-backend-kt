package com.example.backend.dto.tost;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TostDto {

    private UUID id;
    private UUID userId;
    private String text;
    private boolean common;

}
