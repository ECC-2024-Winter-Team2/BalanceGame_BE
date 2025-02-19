package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.Comment;
import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.dto.CategoryQuestionCommentsResponse;
import com.ecc.balancegame.dto.CommentResponseDto;
import com.ecc.balancegame.repository.CategoryRepository;
import com.ecc.balancegame.repository.CommentRepository;
import com.ecc.balancegame.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;

    /**
     * 카테고리 ID와 질문 ID를 받아서,
     * 해당 질문에 달린 댓글 목록을 조회
     */
    @Transactional(readOnly = true)
    public CategoryQuestionCommentsResponse getComments(Long categoryId, Long questionId) {
        // 1) 카테고리 조회
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        // 2) 질문 조회
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("질문을 찾을 수 없습니다."));

        // 3) 질문이 해당 카테고리에 속하는지 검증
        if (!question.getCategory().getCategoryId().equals(categoryId)) {
            throw new IllegalArgumentException("해당 질문은 지정한 카테고리에 속하지 않습니다.");
        }

        // 4) 댓글 목록 조회
        // (A) @Query 방식
        List<Comment> comments = commentRepository.findAllByCategoryIdAndQuestionId(categoryId, questionId);

        // (B) question만으로 조회하는 방식이라면
        // List<Comment> comments = commentRepository.findAllByQuestion(question);

        // 5) 엔티티 -> DTO 변환
        List<CommentResponseDto> commentDtos = comments.stream()
                .map(c -> new CommentResponseDto(
                        c.getUser().getUserName(),   // username
                        c.getContent(),
                        c.isAnonymous(),// content
                        c.getCreatedAt(),           // createdAt
                        c.getLikes()                // likes
                ))
                .collect(Collectors.toList());

        // 6) 최종 응답 구성
        return new CategoryQuestionCommentsResponse(
                category.getCategoryName(),
                questionId,
                commentDtos
        );
    }
}

