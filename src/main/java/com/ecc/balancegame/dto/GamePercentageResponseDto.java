package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePercentageResponseDto {
    private Long userId;
    private double percentage; // 다수 선택 비율 (0 ~ 100)

    // 질문별로 상세 결과를 알고 싶다면
    private List<QuestionFundamentalDto> questions;
}

