package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionFundamentalDto {
    private Long questionId;
    private Long userChoiceId;
    private boolean isFundamental; // 이 질문에서 다수 선택과 일치하는지 여부
}
