package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.dto.ChoiceCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectChoiceRepository extends JpaRepository<SelectChoice, Long> {

    List<SelectChoice> findByQuestion(Question question);

    @Query("SELECT new com.ecc.balancegame.dto.ChoiceCountDto(sc.choiceId, COUNT(sc)) " +
            "FROM SelectChoice sc " +
            "WHERE sc.question.questionId = :questionId " +
            "GROUP BY sc.choiceId")
    List<ChoiceCountDto> countByQuestionIdGroupByChoiceId(@Param("questionId") Long questionId);

    long countByQuestion_QuestionId(Long questionId);

    long countByChoiceId(Long choiceId);

    List<SelectChoice> findByQuestion_QuestionId(Long questionId);
}
