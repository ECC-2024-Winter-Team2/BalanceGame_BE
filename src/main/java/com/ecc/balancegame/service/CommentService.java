package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.*;
import com.ecc.balancegame.dto.*;
import com.ecc.balancegame.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final CategoryService categoryService;
    private final QuestionService questionService;
    private final CommentLikesRepository commentLikesRepository;
    private final SelectChoiceRepository selectChoiceRepository;
    private final UserRepository userRepository;

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

    @Transactional
    public ResponseDto create(CommentRequestDto commentRequestDto) {

        Question question = questionRepository.findById(commentRequestDto.getQuestionId())
                .orElseThrow(()-> new IllegalArgumentException("해당 질문이 존재하지 않습니다."));

        User user = userRepository.findByUserName(commentRequestDto.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        if (commentRequestDto.getPassword() == null || commentRequestDto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("패스워드를 입력해주세요.");
        }

        Comment comment = Comment.createComment(commentRequestDto, question, user);

        commentRepository.save(comment);

        return new ResponseDto("success", "댓글이 작성되었습니다.");
    }

    @Transactional
    public ResponseDto update(Long commentId, CommentUpdateDto commentUpdateDto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new RuntimeException("해당 댓글이 존재하지 않습니다."));

        if (!comment.getPassword().equals((commentUpdateDto.getPassword()))){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        comment.setContent(commentUpdateDto.getContent());

        commentRepository.save(comment);

        return new ResponseDto("success", "댓글이 수정되었습니다.");
    }

    public LikeResponseDto addLike(Long commentId, LikeRequestDto request) {
        // 1) 사용자, 댓글 존재 여부 확인
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));

        // 2) 이미 좋아요를 눌렀는지 확인
        if (commentLikesRepository.existsByCommentAndUser(comment, user)) {
            throw new RuntimeException("이미 좋아요를 눌렀습니다.");
        }

        // 3) 좋아요 추가
        CommentLikes commentLike = new CommentLikes(user, comment);
        commentLikesRepository.save(commentLike);

        // 4) 좋아요 개수 증가
        comment.setLikes(comment.getLikes() + 1);
        commentRepository.save(comment);

        return new LikeResponseDto("좋아요가 추가되었습니다.", comment.getLikes());
    }

    /**
     * 댓글 좋아요 취소
     */
    public LikeResponseDto removeLike(Long commentId, LikeRequestDto request) {
        // 1) 사용자, 댓글 존재 여부 확인
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));

        // 2) 좋아요 기록 조회
        CommentLikes commentLike = commentLikesRepository.findByCommentAndUser(comment, user)
                .orElseThrow(() -> new RuntimeException("좋아요를 누른 적이 없습니다."));

        // 3) 좋아요 취소
        commentLikesRepository.delete(commentLike);

        // 4) 좋아요 개수 감소
        comment.setLikes(comment.getLikes() - 1);
        commentRepository.save(comment);

        return new LikeResponseDto("좋아요가 취소되었습니다.", comment.getLikes());
    }


}
