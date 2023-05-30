package com.example.backend.helpers.utils;

public interface ChatGPTUtils {

    String SPECIAL_SYMBOLS_TO_EXCLUDE = "\n";
    String TAROT_DEFAULT_TEMPLATE = "Hi, GPT. Tell me the TAROT. My question is - \"%s\". My cards are \"%s\"";
    String TAROT_DEFAULT_TEMPLATE_LONG = "Hi, GPT. Tell me the TAROT." +
        " My question is - \"%s\"." +
        " My cards are \"%s\"." +
        " Conditions: " +
        "1. Do not explain every single card, write just common prediction. " +
        "2. Pay attention there are might be reversed cards. It have special meaning." +
        "3. Create prediction by at least 2000 symbols.";
}
