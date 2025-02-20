package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserGameRawDataResponse {
    private Long userId;
    private Long categoryId;
    private List<QuestionDistribution> questions;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class QuestionDistribution {
        private Long questionId;
        private Long userChoiceId;
        private List<ChoiceCount> distribution;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ChoiceCount {
        private Long choiceId;
        private Long count;
    }
}
