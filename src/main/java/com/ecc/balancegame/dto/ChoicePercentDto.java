package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChoicePercentDto {
    private int choiceNumber;   // 1번, 2번...
    private double percentage;  // 38.0, 62.0 등
}
