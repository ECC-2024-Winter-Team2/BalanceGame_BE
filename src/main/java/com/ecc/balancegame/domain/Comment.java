package com.ecc.balancegame.domain;

import com.ecc.balancegame.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    // 어떤 질문에 대한 댓글인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    // 댓글 작성 유저
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 댓글 내용
    @Column(nullable = false)
    private String content;

    // 좋아요(추천) 수
    @Column(nullable = false)
    private int likes;

    // 익명 여부
    @Column(nullable = false)
    private boolean anonymous;

    //댓글 비밀번호
    @Column(nullable = false)
    private String password;

    // 작성 시각
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public static Comment createComment(CommentRequestDto commentRequestDto, Question question, User user) {

        if (commentRequestDto.getPassword() == null || commentRequestDto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("패스워드를 입력해주세요.");
        }

        return Comment.builder()
                .question(question)
                .user(user)
                .content(commentRequestDto.getContent())
                .likes(0)
                .anonymous(commentRequestDto.isAnonymous())
                .password(commentRequestDto.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
    }
}