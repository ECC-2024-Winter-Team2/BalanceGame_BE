package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.CategoryCommentsDto;
import com.ecc.balancegame.dto.CommentResponseDto;
import com.ecc.balancegame.dto.LikeRequestDto;
import com.ecc.balancegame.dto.LikeResponseDto;
import com.ecc.balancegame.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public CategoryCommentsDto getComments(@RequestParam Long categoryId, @RequestParam Long questionId){
        return commentService.getCommentsByCategoryIdAndQuestionId(categoryId, questionId);
    }
    @PostMapping("/{commentId}/like")
    public ResponseEntity<LikeResponseDto> addLike(
            @PathVariable Long commentId,
            @RequestBody LikeRequestDto request) {
        LikeResponseDto response = commentService.addLike(commentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}/like")
    public ResponseEntity<LikeResponseDto> removeLike(
            @PathVariable Long commentId,
            @RequestBody LikeRequestDto request) {
        LikeResponseDto response = commentService.removeLike(commentId, request);
        return ResponseEntity.ok(response);
    }
}
