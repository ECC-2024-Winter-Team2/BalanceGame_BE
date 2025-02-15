package com.ecc.balancegame.dto;

import java.util.List;

public class UserPercentageResponse {
    private Long userId;
    private Long categoryId;
    private double matchPercentage;
    private List<QuestionResult> result;

    public UserPercentageResponse(Long userId, Long categoryId, double matchPercentage, List<QuestionResult> result) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.matchPercentage = matchPercentage;
        this.result = result;
    }

    public Long getUserId() { return userId; }
    public Long getCategoryId() { return categoryId; }
    public double getMatchPercentage() { return matchPercentage; }
    public List<QuestionResult> getResult() { return result; }
}
