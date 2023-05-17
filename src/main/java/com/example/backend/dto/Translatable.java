package com.example.backend.dto;

import com.example.backend.helpers.Language;

public interface Translatable {
    Language from();
    Language to();
    String text();
}
