package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceResultDto {
    private String selectchoice;  // 선택지 내용
    private double percentage;    // (vote / totalAnswers) * 100
    private long vote;           // 몇 명이 이 선택지를 골랐는지
}
