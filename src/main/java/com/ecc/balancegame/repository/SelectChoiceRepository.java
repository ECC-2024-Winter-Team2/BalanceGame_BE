package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.dto.ChoiceCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectChoiceRepository extends JpaRepository<SelectChoice, Long> {
    List<SelectChoice> findByQuestion(Question question);

    // 1) userId로 해당 유저가 선택한 모든 기록 조회
    List<SelectChoice> findAllByUserId(Long userId);

    @Query("SELECT new com.ecc.balancegame.dto.ChoiceCountDto(sc.choiceId, COUNT(sc)) " +
            "FROM SelectChoice sc " +
            "WHERE sc.questionId = :questionId " +
            "GROUP BY sc.choiceId")
    List<ChoiceCountDto> countByQuestionIdGroupByChoiceId(@Param("questionId") Long questionId);

        // 해당 질문에 대해, 전체 유저가 선택한 기록 수
        long countByQuestionId(Long questionId);

        // 특정 보기(Choice)가 몇 번 선택되었는지
        long countByChoiceId(Long choiceId);


}



