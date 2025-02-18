package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResultDto {
    private String question;                // 질문 내용
    private List<ChoiceResultDto> result;   // 이 질문의 선택지 결과 목록
}
