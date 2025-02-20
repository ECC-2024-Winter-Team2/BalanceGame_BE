package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Comment;
import com.ecc.balancegame.dto.CategoryCommentsDto;
import com.ecc.balancegame.dto.CommentResponseDto;
import com.ecc.balancegame.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CategoryService categoryService;

    public CategoryCommentsDto getCommentsByCategoryIdAndQuestionId(Long categoryId, Long questionId){

        // 카테고리 ID에 해당하는 카테고리 이름 가져오기
        String categoryName = categoryService.getCategoryNameById(categoryId);

        // 댓글 목록 가져오기
        List<Comment> comments = commentRepository.findAllByCategoryIdAndQuestionId(categoryId, questionId);

        // CommentResponseDto로 변환
        List<CommentResponseDto> commentResponseDtos = comments.stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());

        // CategoryCommentDto 반환
        return new CategoryCommentsDto(categoryId, categoryName, commentResponseDtos);

    }
}
