package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.CommentLikes;
import com.ecc.balancegame.domain.Comment;
import com.ecc.balancegame.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    // 특정 댓글에 대해 특정 사용자가 좋아요를 눌렀는지 확인
    Optional<CommentLikes> findByCommentAndUser(Comment comment, User user);

    // 특정 댓글에 대해 특정 사용자가 좋아요를 눌렀는지 여부 확인
    boolean existsByCommentAndUser(Comment comment, User user);
}
