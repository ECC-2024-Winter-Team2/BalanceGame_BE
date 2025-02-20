package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryResultResponseDto {
    private String categoryName;
    private List<QuestionPercentDto> results;
}
