package com.ecc.balancegame.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCommentsResponse {
    private Long categoryId;
    private String categoryName;
    private List<CommentResponseDto> comments;
}