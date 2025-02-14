package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SelectChoiceRepository extends JpaRepository<SelectChoice, Long> {
    List<SelectChoice> findByQuestion(Question question);
}



