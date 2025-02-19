package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT c FROM Comment c " +
            "WHERE c.question.questionId = :questionId " +
            "AND c.question.category.categoryId = :categoryId")
    List<Comment> findAllByCategoryIdAndQuestionId(
            @Param("categoryId") Long categoryId,
            @Param("questionId") Long questionId
    );
}