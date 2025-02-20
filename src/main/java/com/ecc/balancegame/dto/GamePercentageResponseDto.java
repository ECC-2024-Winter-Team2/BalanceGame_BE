package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 사용자 퍼센트 조회 결과 DTO
 */
@Getter
@Setter
@AllArgsConstructor
public class GamePercentageResponseDto {
    private Long userId;
    private Long categoryId;
    private List<QuestionResultDto> result;
}
