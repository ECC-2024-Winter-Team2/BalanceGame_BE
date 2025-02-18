package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;
import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<SelectChoice> findByQuestionId(Long questionId);

}
