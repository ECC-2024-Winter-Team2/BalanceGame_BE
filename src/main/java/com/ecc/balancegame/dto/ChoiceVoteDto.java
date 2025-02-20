package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ChoiceVoteDto {
    private String selectedChoice; // 선택지 내용
    private double percentage; // 백분율
    private long vote; // 선택한 사람 수
}
