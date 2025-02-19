package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
