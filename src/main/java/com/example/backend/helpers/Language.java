package com.example.backend.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Language {
    EN("en"),
    RU("ru"),

    ;

    private final String code;
}
