package com.example.backend.contollers;

import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import com.example.backend.helpers.Language;
import com.example.backend.helpers.YandexTranslateHelper;
import com.example.backend.service.TarotService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarot")
@AllArgsConstructor
public class TarotController {

    private final TarotService tarotService;

    @GetMapping("/cards")
    public List<TarotCardDto> allCards() {
        return tarotService.getAllCards();
    }


    @PostMapping("/question")
    public TarotResponse ask(@RequestBody TarotRequest request) throws Exception {
        return tarotService.ask(request);
    }


    @GetMapping("/take-a-try")
    public TarotCardDto takeATry() {
        return tarotService.randomOne();
    }


//    private final YandexTranslateHelper yandexTranslateHelper;
//    @PostMapping("/translate")
//    public String translate(@RequestBody Text text) throws IOException, URISyntaxException, InterruptedException {
//        return yandexTranslateHelper.translate(text.value, Language.EN, Language.RU);
//    }
//
//    @Data
//    static class Text {
//        private String value;
//    }
}
