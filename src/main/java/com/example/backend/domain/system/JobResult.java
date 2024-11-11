package com.example.backend.domain.system;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResult {

    private UUID id;

    private Instant createDate;

    private Instant updateDate;

    private String data;

    private String type;


}
