package com.ecc.balancegame.repository;

import com.ecc.balancegame.dto.QuestionResult;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserPercentageRepository {

    public List<QuestionResult> getUserResults(Long userId, Long categoryId) {
        // 예제 데이터 (DB 대신 하드코딩)
        return Arrays.asList(
                new QuestionResult(101L, true),
                new QuestionResult(102L, false),
                new QuestionResult(103L, true),
                new QuestionResult(104L, true)
        );
    }
}
