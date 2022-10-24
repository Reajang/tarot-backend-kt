package com.example.backend.contollers;


import com.example.backend.dto.TostDto;
import com.example.backend.repository.TostRepository;
import com.example.backend.service.TostService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tost")
@AllArgsConstructor
public class TostController {

    private final TostService tostService;

    @GetMapping("/{userId}")
    public List<TostDto> getList(@PathVariable(required = false) UUID userId) {
        return tostService.getList(userId);
    }

    @PostMapping("/{userId}")
    public TostDto getList(@PathVariable UUID userId,
                           @RequestParam String text) {
        return tostService.create(userId, text);
    }

    @PutMapping("/{userId}")
    public void getList(@PathVariable UUID userId,
                        @RequestParam UUID tostId,
                        @RequestParam String text) {
        tostService.update(userId, tostId, text);
    }
}
