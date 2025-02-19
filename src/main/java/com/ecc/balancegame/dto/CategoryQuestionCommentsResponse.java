package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryQuestionCommentsResponse {
    private String categoryName;          // "food"
    private Long questionId;              // 101
    private List<CommentResponseDto> comments;
}
