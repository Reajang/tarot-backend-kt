package com.example.backend.helpers.utils;

public interface ChatGPTUtils {

    String SPECIAL_SYMBOLS_TO_EXCLUDE = "\n";
    String TAROT_DEFAULT_TEMPLATE = "Hi, GPT. Tell me the TAROT. My question is - \"%s\". My cards are \"%s\"";
    String TAROT_DEFAULT_TEMPLATE_LONG = "Hi, GPT. Tell me the TAROT." +
        " My question is - \"%s\"." +
        " My cards are \"%s\"." +
        " Conditions: " +
        "1. Make a prediction for 2000 characters.";
}
