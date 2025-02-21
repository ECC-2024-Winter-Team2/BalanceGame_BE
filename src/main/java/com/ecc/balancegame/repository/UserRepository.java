package com.ecc.balancegame.repository;

import com.ecc.balancegame.domain.*;
import com.ecc.balancegame.domain.UserChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 특정 질문에 대한 모든 사용자 선택 조회
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserId(Long userId);
    boolean existsByUserName(String userName);
}
