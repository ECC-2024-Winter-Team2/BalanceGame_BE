package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.ChoiceRequestDto;
import com.ecc.balancegame.service.ChoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/choices")
public class ChoiceController {

    private ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @PostMapping
    public ResponseEntity<String> saveChoices(@RequestBody ChoiceRequestDto choiceRequestDto) {
        choiceService.saveChoices(choiceRequestDto);
        return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"선택되었습니다.\"}");
    }
}

