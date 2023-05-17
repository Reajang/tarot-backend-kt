package com.example.backend.helpers;

import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatGPTHelper {

    @Value("${gpt.api.url}")
    private String url;
    @Value("${gpt.api.auth.bearer}")
    private String auth;
    @Value("${gpt.api.model}")
    private String model;
    @Value("${gpt.api.max_tokens}")
    private String maxTokens;
    @Value("${gpt.api.temperature}")
    private String temperature;

    private final ObjectMapper objectMapper;

    public TarotResponse tarotMeChatGPT(TarotRequest request) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", auth);

        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("model", model);
        objectNode.put("prompt", prepareQuestion(request));
        objectNode.put("max_tokens", Long.parseLong(maxTokens));
        objectNode.put("temperature", Double.parseDouble(temperature));

        con.setDoOutput(true);
        con.getOutputStream().write(objectNode.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines().reduce((a, b) -> a + b).get();

        String gptResponse = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(objectMapper.readTree(output).get("choices").iterator(), Spliterator.ORDERED), false)
            .map(jsonNode -> jsonNode.get("text").asText()).collect(Collectors.joining("\n"));

        return new TarotResponse(request.cards(), gptResponse);
    }

    private String prepareQuestion(TarotRequest request) {
        String pattern = "Hi, GPT. Tell me the TAROT without explaining each card. My question is - \"%s\". My cards are \"%s\"";
        String cards = request.cards().stream().map(card -> {
            if (card.reversed()) {
                return card.name() + " reversed";
            }
            return card.name();
        }).collect(Collectors.joining(", "));
        return String.format(pattern, request.text(), cards);
    }

}
