package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<UserChoice, Long> {

    // 1) 특정 질문에 대한 모든 사용자 선택
    List<UserChoice> findByQuestion(Question question);

    // 2) 특정 카테고리에 속한 모든 사용자 선택
    List<UserChoice> findByCategory(Category category);

    // 3) 특정 사용자+카테고리에 대한 선택들
    List<UserChoice> findByUserAndCategory(User user, Category category);

    // 4) 특정 카테고리+질문에 대한 선택들
    List<UserChoice> findByCategoryAndQuestion(Category category, Question question);
}
