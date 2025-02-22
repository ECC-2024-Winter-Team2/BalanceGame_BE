package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.*;
import com.ecc.balancegame.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping
    public CategoryCommentsDto getComments(@RequestParam Long categoryId, @RequestParam Long questionId){
        return commentService.getCommentsByCategoryIdAndQuestionId(categoryId, questionId);
    }

    // 댓글 생성
    @PostMapping
    public ResponseEntity<ResponseDto> create(@RequestBody CommentRequestDto commentRequestDto) {
        try {
            ResponseDto response = commentService.create(commentRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            ResponseDto response = new ResponseDto("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (RuntimeException e) {
            ResponseDto response = new ResponseDto("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<ResponseDto> update(@PathVariable Long commentId, @RequestBody CommentUpdateDto commentUpdateDto){
        ResponseDto response = commentService.update(commentId, commentUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
