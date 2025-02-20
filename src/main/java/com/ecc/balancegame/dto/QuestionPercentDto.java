package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionPercentDto {
    private String questionText;
    private String selectedChoice;
    private double percentage;
}
