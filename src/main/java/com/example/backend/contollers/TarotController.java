package com.example.backend.contollers;

import com.example.backend.dto.tarot.TarotCardDto;
import com.example.backend.dto.tarot.TarotRequest;
import com.example.backend.dto.tarot.TarotResponse;
import com.example.backend.service.TarotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public TarotResponse ask(@RequestBody TarotRequest request) {
        return tarotService.ask(request);
    }

    /**
     * @return Async Job id
     */
    @PostMapping("/question/async")
    public UUID askAsync(@RequestBody TarotRequest request) {
        return tarotService.askAsync(request);
    }


    @GetMapping("/take-a-try")
    public TarotCardDto takeATry() {
        return tarotService.randomOne();
    }
}
