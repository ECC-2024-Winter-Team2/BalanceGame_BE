package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.CategoryResultResponseDto;
import com.ecc.balancegame.dto.GamePercentageResponseDto;
import com.ecc.balancegame.dto.GameResultsResponseDto;
import com.ecc.balancegame.dto.QuestionPercentResponseDto;
import com.ecc.balancegame.service.UserChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameResultController {

    private final UserChoiceService userChoiceService;

    /**
     * 사용자의 퍼센트 조회 (다수 선택과 얼마나 일치하는지)
     * GET /api/game/{userId}
     *
     * @param userId 사용자 ID
     * @return GamePercentageResponseDto
     */
    @GetMapping("/{userId}")
    public ResponseEntity<GamePercentageResponseDto> getUserPercentage(@PathVariable Long userId) {
        GamePercentageResponseDto response = userChoiceService.getUserPercent(userId);
        return ResponseEntity.ok(response);
    }

    /**
     * 질문별 퍼센트 결과 조회 (카테고리별 질문들의 선택지 비율 조회)
     * GET /api/game/result/{categoryId}
     *
     * @param categoryId 카테고리 ID
     * @return List<QuestionPercentResponseDto>
     */
    @GetMapping("/result/{categoryId}")
    public ResponseEntity<List<QuestionPercentResponseDto>> getCategoryQuestionPercent(@PathVariable Long categoryId) {
        List<QuestionPercentResponseDto> response = userChoiceService.getCategoryQuestionPercent(categoryId);
        return ResponseEntity.ok(response);
    }
    /**
     * 모든 질문의 선택 결과를 한 번에 조회
     * GET /api/game/results
     */
    @GetMapping("/results")
    public ResponseEntity<GameResultsResponseDto> getAllGameResults() {
        GameResultsResponseDto response = userChoiceService.getAllGameResults();
        return ResponseEntity.ok(response);
    }

    /**
     * 카테고리별 퍼센트 결과 조회 (GET 방식)
     * GET /api/game/result?categoryName=음식
     */
    @GetMapping("/result")
    public ResponseEntity<CategoryResultResponseDto> getCategoryGameResult(@RequestParam String categoryName) {
        CategoryResultResponseDto response = userChoiceService.getCategoryGameResult(categoryName);
        return ResponseEntity.ok(response);
    }
}
