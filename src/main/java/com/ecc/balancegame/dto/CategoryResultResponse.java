package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResultResponse {
    private String categoryName;
    private List<QuestionChoiceResult> results;
}
