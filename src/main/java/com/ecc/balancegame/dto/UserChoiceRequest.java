package com.ecc.balancegame.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserChoiceRequest {

    private Long userId;       // 어떤 유저인지
    private Long categoryId;   // 어떤 카테고리인지
    private List<Selection> selections; // 여러 질문에 대한 선택 리스트

    @Getter
    @Setter
    public static class Selection {
        private Long questionId;       // 질문 ID
        private Long choiceId;         // 선택지 ID
        private Integer selectedNumber; // 사용자가 선택한 번호
    }
}
