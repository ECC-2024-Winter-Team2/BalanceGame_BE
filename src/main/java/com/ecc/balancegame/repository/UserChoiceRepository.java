package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChoiceRepository extends JpaRepository<UserChoice, Long> {
    long countByQuestion_QuestionId(Long questionId);
    long countByQuestion_QuestionIdAndChoice_ChoiceId(Long questionId, Long choiceId);
    List<UserChoice> findByUser(User user);
    List<UserChoice> findByQuestion(Question question);

}