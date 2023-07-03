package com.example.backend.dto.common;


import com.example.backend.lang.Language;

public interface Translatable {
    Language from();
    Language to();
    String text();
}
