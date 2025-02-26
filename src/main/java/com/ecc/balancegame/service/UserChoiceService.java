package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.domain.User;
import com.ecc.balancegame.domain.UserChoice;
import com.ecc.balancegame.dto.*;
import com.ecc.balancegame.repository.CategoryRepository;
import com.ecc.balancegame.repository.QuestionRepository;
import com.ecc.balancegame.repository.SelectChoiceRepository;
import com.ecc.balancegame.repository.UserChoiceRepository;
import com.ecc.balancegame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserChoiceService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final UserChoiceRepository userChoiceRepository;
    private final SelectChoiceRepository selectChoiceRepository;

    /**
     * 여러 질문에 대한 사용자 선택 정보를 저장하는 메서드
     * @param request 사용자 선택 정보 DTO
     */
    public void saveUserSelections(UserChoiceRequest request) {
        // 1) 유저, 카테고리 조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

        // 2) 각 선택 항목 처리
        for (UserChoiceRequest.Selection selection : request.getSelections()) {
            // 질문 조회
            Question question = questionRepository.findById(selection.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("해당 질문이 존재하지 않습니다."));
            // 선택지 조회 (이미 DB에 등록된 선택지를 사용)
            SelectChoice choice = selectChoiceRepository.findById(selection.getChoiceId())
                    .orElseThrow(() -> new RuntimeException("해당 선택지가 존재하지 않습니다."));
            // UserChoice 엔티티 생성
            UserChoice userChoice = UserChoice.builder()
                    .user(user)
                    .category(category)
                    .question(question)
                    .choice(choice)
                    .build();
            // DB에 저장
            userChoiceRepository.save(userChoice);
        }
    }
    @Transactional(readOnly = true)
    public GamePercentageResponseDto getUserPercent(Long userId) {
        // 1) 유저 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 존재하지 않습니다."));

        // 2) 사용자가 응답한 모든 질문 가져오기
        List<UserChoice> userChoices = userChoiceRepository.findByUser(user);
        if (userChoices.isEmpty()) {
            throw new RuntimeException("해당 유저의 선택 기록이 없습니다.");
        }

        // 3) 질문별로 isFundamental 값 계산
        List<QuestionResultDto> result = userChoices.stream()
                .map(choice -> {
                    Question question = choice.getQuestion();

                    // 해당 질문에 대한 모든 사용자 선택 가져오기
                    List<UserChoice> questionVotes = userChoiceRepository.findByQuestion(question);

                    Set<Long> choiceIds = questionVotes.stream()
                            .map(uc -> uc.getChoice().getChoiceId())
                            .collect(Collectors.toSet());

                    if (choiceIds.size() != 2) {
                        throw new RuntimeException("해당 질문에는 두 개의 선택지가 있어야 합니다.");
                    }

                    Iterator<Long> iterator = choiceIds.iterator();
                    Long optionAId = iterator.next();
                    Long optionBId = iterator.next();

                    long countOptionA = questionVotes.stream()
                            .filter(uc -> uc.getChoice().getChoiceId().equals(optionAId))
                            .count();

                    long countOptionB = questionVotes.stream()
                            .filter(uc -> uc.getChoice().getChoiceId().equals(optionBId))
                            .count();

                    List<Long> majorityChoices = new ArrayList<>();
                    if (countOptionA > countOptionB) {
                        majorityChoices.add(optionAId);
                    } else if (countOptionB > countOptionA) {
                        majorityChoices.add(optionBId);
                    } else {
                        // 개수가 같으면 둘 다 추가
                        majorityChoices.add(optionAId);
                        majorityChoices.add(optionBId);
                    }

                    // 사용자의 선택이 다수 선택지와 일치하는지 확인
                    boolean isFundamental = majorityChoices.contains(choice.getChoice().getChoiceId()   );

                    return new QuestionResultDto(question.getQuestionId(), isFundamental);
                })
                .collect(Collectors.toList());

        // 4) 최종 응답 DTO 반환
        return new GamePercentageResponseDto(user.getUserId(), null, result);
    }
    /**
     * 특정 카테고리에 속한 질문별 선택 비율(퍼센트) 조회
     * @param categoryId 카테고리 ID
     * @return 질문별 선택 퍼센트 리스트
     */
    @Transactional(readOnly = true)
    public List<QuestionPercentResponseDto> getCategoryQuestionPercent(Long categoryId) {
        // 1) 카테고리 확인
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

        // 2) 해당 카테고리의 모든 질문 조회
        List<Question> questions = questionRepository.findAllByCategory(category);

        // 3) 각 질문별로 UserChoice를 조회하고 선택 퍼센트 계산
        return questions.stream().map(question -> {
            List<UserChoice> userChoices = userChoiceRepository.findByQuestion(question);
            int totalAnswers = userChoices.size();

            // choiceId별 선택 횟수 집계
            Map<Integer, Long> choiceCountMap = userChoices.stream()
                    .collect(Collectors.groupingBy(
                            uc -> uc.getChoice().getChoiceNumber(),
                            Collectors.counting()
                    ));

            // 선택지별 퍼센트 계산
            List<ChoicePercentDto> choicePercentList = choiceCountMap.entrySet().stream()
                    .map(entry -> new ChoicePercentDto(
                            entry.getKey(),
                            (totalAnswers == 0) ? 0.0 : (entry.getValue() * 100.0 / totalAnswers)
                    ))
                    .collect(Collectors.toList());

            return new QuestionPercentResponseDto(question.getQuestionText(), choicePercentList);
        }).collect(Collectors.toList());
    }

        /**
         * 모든 질문에 대한 선택지별 퍼센트 계산 후 반환
         */
        @Transactional(readOnly = true)
        public GameResultsResponseDto getAllGameResults() {
            // 1) 전체 질문 조회
            List<Question> allQuestions = questionRepository.findAll();

            // 2) 질문별 선택지 투표율 계산
            List<QuestionVoteDto> results = allQuestions.stream().map(question -> {
                List<UserChoice> userChoices = userChoiceRepository.findByQuestion(question);
                int totalVotes = userChoices.size();

                // 선택지별 투표 수 계산
                Map<String, Long> choiceCountMap = userChoices.stream()
                        .collect(Collectors.groupingBy(
                                uc -> uc.getChoice().getSelectedText(),
                                Collectors.counting()
                        ));

                // 선택지별 투표 퍼센트 계산
                List<ChoiceVoteDto> choiceVotes = choiceCountMap.entrySet().stream()
                        .map(entry -> new ChoiceVoteDto(
                                entry.getKey(),
                                (totalVotes == 0) ? 0.0 : (entry.getValue() * 100.0 / totalVotes),
                                entry.getValue()
                        ))
                        .collect(Collectors.toList());

                return new QuestionVoteDto(question.getQuestionText(), choiceVotes);
            }).collect(Collectors.toList());

            return new GameResultsResponseDto(results);
        }
    /**
     * 특정 카테고리의 질문별 선택 퍼센트 계산
     * @param categoryName 카테고리 이름
     * @return 카테고리별 질문과 선택 퍼센트 결과
     */
    @Transactional(readOnly = true)
    public CategoryResultResponseDto getCategoryGameResult(String categoryName) {
        // 1) 카테고리 조회
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

        // 2) 해당 카테고리의 모든 질문 조회
        List<Question> questions = questionRepository.findAllByCategory(category);

        // 3) 각 질문별 선택 퍼센트 계산
        List<QuestionPercentDto> results = questions.stream().map(question -> {
            List<UserChoice> userChoices = userChoiceRepository.findByQuestion(question);
            int totalVotes = userChoices.size();

            // 선택지별 투표 수 계산
            Map<String, Long> choiceCountMap = userChoices.stream()
                    .collect(Collectors.groupingBy(
                            uc -> uc.getChoice().getSelectedText(),
                            Collectors.counting()
                    ));

            // 가장 많이 선택된 선택지 찾기
            Map.Entry<String, Long> mostVotedChoice = choiceCountMap.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow(() -> new RuntimeException("투표 데이터가 없습니다."));

            // 퍼센트 계산
            double percentage = (totalVotes == 0) ? 0.0 : (mostVotedChoice.getValue() * 100.0 / totalVotes);

            return new QuestionPercentDto(question.getQuestionText(), mostVotedChoice.getKey(), percentage);
        }).collect(Collectors.toList());

        return new CategoryResultResponseDto(categoryName, results);
    }

    @Transactional
    public void resetUserChoices(Long userId) {
        if (!userRepository.existsById(userId)){
            throw new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.");
        }
        userRepository.deleteByUserId(userId);
    }
}

