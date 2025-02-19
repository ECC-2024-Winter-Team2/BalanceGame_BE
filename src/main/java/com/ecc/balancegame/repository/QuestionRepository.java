package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.category.categoryId = :categoryId ORDER BY q.orderIndex ASC")
    List<Question> findByCategoryIdOrderByOrderIndex(@Param("categoryId") Long categoryId);

    List<Question> findByCategoryId(Long categoryId);

    List<Question> findByCategory(Category category);


}
