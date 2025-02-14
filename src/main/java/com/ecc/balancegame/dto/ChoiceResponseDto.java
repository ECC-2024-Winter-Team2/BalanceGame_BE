package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChoiceResponseDto {
    private Long choiceId;
    private Integer choiceNumber;
    private String choiceText;
}
