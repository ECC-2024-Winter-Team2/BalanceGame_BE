package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionResponseDto {
    private Long questionId;
    private String questionText;
    private Long categoryId;
    private Integer orderIndex;
    private List<ChoiceResponseDto> choices;
}
