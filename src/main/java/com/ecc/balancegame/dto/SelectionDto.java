package com.ecc.balancegame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectionDto {
    private Long questionId;
    private Long choiceId;
    private Integer selectedNumber;
}
