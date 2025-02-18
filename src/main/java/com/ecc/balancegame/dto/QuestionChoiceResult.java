package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChoiceResult {
    private String questionText;   // 질문 내용
    private String selectionText;  // 선택지 내용
    private long count;            // 몇 명이 골랐는지
    private double percentage;     // (count / 질문에 대한 total) * 100
}
