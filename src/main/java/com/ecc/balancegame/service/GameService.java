package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.*;
import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.dto.*;
import com.ecc.balancegame.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public GamePercentageResponseDto getUserPercent(Long userId, Long categoryId) {
        // 1) 유저, 카테고리 조회 (소문자 userRepository 사용)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

        // 2) 카테고리에 속한 질문 목록 조회
        List<Question> questions = questionRepository.findAllByCategory(category);

        // 3) result 리스트 구성 (isFundamental은 일단 false로 내려줌)
        List<QuestionResultDto> result = questions.stream()
                .map(q -> new QuestionResultDto(q.getQuestionId(), false))
                .collect(Collectors.toList());

        // 4) 최종 응답 DTO
        return new GamePercentageResponseDto(user.getUserId(), category.getCategoryId(), result);
    }


}
