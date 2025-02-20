package com.ecc.balancegame.controller;

import com.ecc.balancegame.dto.CategoryCommentsDto;
import com.ecc.balancegame.dto.CommentResponseDto;
import com.ecc.balancegame.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
