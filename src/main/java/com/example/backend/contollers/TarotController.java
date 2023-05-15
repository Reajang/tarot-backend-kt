package com.example.backend.contollers;

import com.example.backend.dto.TarotCardDto;
import com.example.backend.dto.TarotRequest;
import com.example.backend.dto.TarotResponse;
import com.example.backend.service.TarotService;
import java.util.List;
import lombok.AllArgsConstructor;
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
}
