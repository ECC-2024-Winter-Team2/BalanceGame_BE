package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoicePercentageDto {
    private String choiceName;  // 보기 내용
    private double percentage;  // 퍼센트(0~100)
}
