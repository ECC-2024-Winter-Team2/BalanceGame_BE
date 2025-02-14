package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.QuestionResponseDto;
import com.ecc.balancegame.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/api/categories/{categoryId}/questions")
    public List<QuestionResponseDto> getQuestions(@PathVariable Long categoryId){
        return questionService.getQuestionsByCategory(categoryId);
    }
}
