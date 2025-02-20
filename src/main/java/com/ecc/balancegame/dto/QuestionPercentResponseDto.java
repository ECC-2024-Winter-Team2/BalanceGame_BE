package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionPercentResponseDto {
    private String questionText;           // 질문 내용
    private List<ChoicePercentDto> results; // 선택지별 퍼센트
}
