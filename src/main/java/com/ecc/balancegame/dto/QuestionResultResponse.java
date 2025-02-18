package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultResponse {
    private String question;                    // 질문 내용
    private List<ChoicePercentageDto> result;   // 보기별 퍼센트 목록
}

