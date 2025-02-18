package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.AllGameResultsResponse;
import com.ecc.balancegame.dto.CategoryResultResponse;
import com.ecc.balancegame.dto.GamePercentageResponseDto;
import com.ecc.balancegame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor

public class GameController {
    private final GameService gameService;

    @GetMapping("/{userId}")
    public ResponseEntity<GamePercentageResponseDto> getUserGamePercentage(@PathVariable Long userId) {
        // Service에서 계산 로직 처리
        GamePercentageResponseDto responseDto = gameService.calculateUserFundamentalPercentage(userId);
        return ResponseEntity.ok(responseDto);
    }
    // GET /api/game/result?categoryName=XXX
    @GetMapping("/result")
    public ResponseEntity<CategoryResultResponse> getCategoryResult(@RequestParam String categoryName) {
        CategoryResultResponse response = gameService.getCategoryResult(categoryName);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/results")
    public ResponseEntity<AllGameResultsResponse> getAllResults() {
        AllGameResultsResponse response = gameService.getAllGameResults();
        return ResponseEntity.ok(response);
    }
}
