package com.ecc.balancegame.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChoiceRequestDto {
    private Long userId;
    private Long categoryId;
    private List<SelectionDto> selections;
}

