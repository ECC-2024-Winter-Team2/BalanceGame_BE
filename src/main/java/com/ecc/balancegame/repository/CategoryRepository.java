package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 만약 이름으로 검색할 일이 있다면
    Optional<Category> findByCategoryName(String categoryName);
}


