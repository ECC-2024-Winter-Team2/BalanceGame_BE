package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
