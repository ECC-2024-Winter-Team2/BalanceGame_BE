package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryCommentsDto {
    private Long categoryId;
    private String categoryName;
    private List<CommentResponseDto> comments;
}
