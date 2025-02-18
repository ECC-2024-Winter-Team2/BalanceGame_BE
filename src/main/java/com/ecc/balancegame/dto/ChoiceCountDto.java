package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceCountDto {
    private Long choiceId;
    private long count;
}
