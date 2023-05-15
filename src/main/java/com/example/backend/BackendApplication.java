package com.example.backend;

import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);
//        System.out.println(tarotMeChatGPT(new TarotRequest(List.of(
//            new TarotCardDto("The Hanged Man"),
//            new TarotCardDto("The World"),
//            new TarotCardDto("Queen of Wands")
//        ), "When I will found a new job")));
    }

    public static TarotResponse tarotMeChatGPT(TarotRequest request) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("https://api.openai.com/v1/completions").openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer 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");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("model", "text-davinci-003");
        objectNode.put("prompt", prepareQuestion(request));
        objectNode.put("max_tokens", 4000);
        objectNode.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(objectNode.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
            .reduce((a, b) -> a + b).get();

        String gptResponse = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(objectMapper.readTree(output).get("choices").iterator(), Spliterator.ORDERED),
                false)
            .map(jsonNode -> jsonNode.get("text").asText())
            .collect(Collectors.joining("\n"));

        return new TarotResponse(
            request.cards(),
            gptResponse
        );
    }

    private static String prepareQuestion(TarotRequest request) {
        String pattern = "Hi, GPT. Tell me the TAROT. My question is - \"%s\". My cards are \"%s\"";
        String cards = request.cards()
            .stream()
            .map(TarotCardDto::name)
            .collect(Collectors.joining(", "));
        return String.format(pattern, request.question(), cards);
    }
}
