package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
    long countByQuestionId(Long questionId);
    long countByQuestionIdAndChoiceId(Long questionId, Long choiceId);
}