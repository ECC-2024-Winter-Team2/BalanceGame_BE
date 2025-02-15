package com.ecc.balancegame.service;

import com.ecc.balancegame.dto.QuestionResult;
import com.ecc.balancegame.dto.UserPercentageResponse;
import com.ecc.balancegame.repository.UserPercentageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPercentageService {

    private final UserPercentageRepository userPercentageRepository;

    public UserPercentageService(UserPercentageRepository userPercentageRepository) {
        this.userPercentageRepository = userPercentageRepository;
    }

    public UserPercentageResponse getUserPercentage(Long userId, Long categoryId) {
        // 사용자 데이터 조회
        List<QuestionResult> results = userPercentageRepository.getUserResults(userId, categoryId);

        // 다수 선택과 일치하는 개수 계산
        long matchingCount = results.stream().filter(QuestionResult::isFundamental).count();
        double matchPercentage = (results.isEmpty()) ? 0.0 : (matchingCount * 100.0 / results.size());

        return new UserPercentageResponse(userId, categoryId, matchPercentage, results);
    }
}
