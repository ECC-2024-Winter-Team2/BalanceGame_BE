package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.CategoryQuestionCommentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 예시) GET /api/comments/{categoryId}?questionId=101
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryQuestionCommentsResponse> getComments(
            @PathVariable Long categoryId,
            @RequestParam Long questionId
    ) {
        CategoryQuestionCommentsResponse response =
                commentService.getComments(categoryId, questionId);
        return ResponseEntity.ok(response);
    }
}
