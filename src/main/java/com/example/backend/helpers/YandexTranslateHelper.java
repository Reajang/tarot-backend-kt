package com.example.backend.helpers;

import static org.apache.commons.lang3.StringUtils.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class YandexTranslateHelper {

    @Value("${yandex.api.translate.url}")
    private String url;
    @Value("${yandex.api.translate.auth.type}")
    private String authType;
    @Value("${yandex.api.translate.auth.apikey}")
    private String apiKey;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public String translate(String textToTranslate, Language sourceLanguage, Language targetLanguage)
        throws IOException, InterruptedException, URISyntaxException {
        log.info("Start translation via Yandex API text={} from={} to{}",
            textToTranslate.length() > 50 ? textToTranslate.substring(0, 50) + "..." : textToTranslate,
            sourceLanguage.getCode(),
            targetLanguage.getCode()
        );

        HttpRequest httpRequest = prepareHttpRequest(textToTranslate, sourceLanguage, targetLanguage);

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = objectMapper.readTree(httpResponse.body());

        log.info("Response from Yandex API: {}", jsonNode);

        JsonNode firstTranslation = jsonNode.get("translations").get(0);
        return deleteYandexSystemParentheses(firstTranslation.get("text").asText());
    }

    private HttpRequest prepareHttpRequest(String textToTranslate, Language sourceLanguage, Language targetLanguage)
        throws URISyntaxException {
        return HttpRequest.newBuilder()
            .POST(HttpRequest.BodyPublishers.ofByteArray(prepareBody(textToTranslate, sourceLanguage, targetLanguage)))
            .uri(new URI(url))
            .headers(
                "Content-Type", "application/json",
                "Authorization", authType + SPACE + apiKey
            )
            .build();
    }

    private byte[] prepareBody(String textToTranslate, Language sourceLanguage, Language targetLanguage) {
        ObjectNode objectNode = objectMapper.createObjectNode();

        String[] texts = new String[] {ObjectUtils.defaultIfNull(textToTranslate, EMPTY)};

        objectNode.put("sourceLanguageCode", sourceLanguage.getCode());
        objectNode.put("targetLanguageCode", targetLanguage.getCode());
        objectNode.put("texts", Arrays.toString(texts));

        return objectNode.toString().getBytes();
    }

    private String deleteYandexSystemParentheses(String translateResponseWithinParentheses) {
        if (StringUtils.isBlank(translateResponseWithinParentheses)) {
            return EMPTY;
        }
        int startIndex = 0;
        int endIndex = translateResponseWithinParentheses.length();
        boolean stringHadSystemParentheses = false;

        if (translateResponseWithinParentheses.charAt(0) == '[') {
            startIndex++;
            stringHadSystemParentheses = true;
        }
        if (translateResponseWithinParentheses.charAt(translateResponseWithinParentheses.length() - 1) == ']') {
            endIndex--;
            stringHadSystemParentheses = true;
        }
        return stringHadSystemParentheses
            ? translateResponseWithinParentheses.substring(startIndex, endIndex)
            : translateResponseWithinParentheses;
    }

}
