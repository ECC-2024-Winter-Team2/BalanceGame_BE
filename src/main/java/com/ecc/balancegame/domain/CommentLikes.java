package com.ecc.balancegame.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"comment_id", "user_id"}) // 동일 유저의 중복 좋아요 방지
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    // 어떤 댓글에 대한 좋아요인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    // 댓글 좋아요를 누른 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 해당 댓글이 속한 질문
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    // 선택지 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id", nullable = false)
    private SelectChoice choice;

    // 좋아요를 누른 시간
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    // 사용자와 댓글만 받아서 생성하는 편의 생성자
    public CommentLikes(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        // 예를 들어, 댓글이 속한 질문은 comment.getQuestion()로 설정할 수 있음.
        this.question = comment.getQuestion();
        // 선택지가 좋아요와 관련 없으면 null로 두거나 기본값 지정
        this.choice = null;
        this.createdAt = LocalDateTime.now();
    }
}
