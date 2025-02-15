package com.ecc.balancegame.dto;

public class QuestionResult {
    private Long questionId;
    private boolean isFundamental;

    public QuestionResult(Long questionId, boolean isFundamental) {
        this.questionId = questionId;
        this.isFundamental = isFundamental;
    }

    public Long getQuestionId() { return questionId; }
    public boolean isFundamental() { return isFundamental; }
}
