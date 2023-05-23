package com.example.backend.helpers;

import static com.example.backend.helpers.utils.ChatGPTUtils.*;
import static java.util.Spliterators.*;
import static org.apache.commons.lang3.StringUtils.*;

import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatGPTHelper {

    @Value("${gpt.api.url}")
    private String url;
    @Value("${gpt.api.auth.type}")
    private String authType;
    @Value("${gpt.api.auth.bearer}")
    private String bearer;
    @Value("${gpt.api.model}")
    private String model;
    @Value("${gpt.api.max_tokens}")
    private String maxTokens;
    @Value("${gpt.api.temperature}")
    private String temperature;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public TarotResponse tarotMeChatGPT(TarotRequest request) throws Exception {
        HttpRequest httpRequest = prepareHttpRequest(request);
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //Бывает клиент возвращает ошибки
        JsonNode responseBody = objectMapper.readTree(httpResponse.body());

        String gptResponse = StreamSupport.stream(
                spliteratorUnknownSize(responseBody.get("choices").iterator(), Spliterator.ORDERED),
                false)
            .filter(Objects::nonNull)
            .map(jsonNode -> jsonNode.get("text").asText())
            .filter(StringUtils::isNotBlank)
            .map(answer -> answer.replaceAll(SPECIAL_SYMBOLS_TO_EXCLUDE, EMPTY))
            .collect(Collectors.joining("\n"));

        return new TarotResponse(request.cards(), gptResponse);
    }

    private HttpRequest prepareHttpRequest(TarotRequest request) throws URISyntaxException {
        return HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofByteArray(prepareBody(request)))
            .uri(new URI(url))
            .headers(
                "Content-Type", "application/json",
                "Authorization", authType + SPACE + bearer
            )
            .build();
    }

    private byte[] prepareBody(TarotRequest request) {
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("model", model);
        objectNode.put("prompt", prepareQuestion(request));
        objectNode.put("max_tokens", Long.parseLong(maxTokens));
        objectNode.put("temperature", Double.parseDouble(temperature));

        return objectNode.toString().getBytes();
    }

    private String prepareQuestion(TarotRequest request) {
        String cards = request.cards().stream().map(card -> {
            if (card.reversed()) {
                return card.name() + " reversed";
            }
            return card.name();
        }).collect(Collectors.joining(", "));
        return String.format(TAROT_DEFAULT_TEMPLATE, request.text(), cards);
    }

}
