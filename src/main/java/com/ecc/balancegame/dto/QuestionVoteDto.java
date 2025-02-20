package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QuestionVoteDto {
    private String question; // 질문 내용
    private List<ChoiceVoteDto> result; // 선택지별 결과
}
