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

    private final SelectChoiceRepository selectChoiceRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final CategoryRepository categoryRepository;
    private final UserChoiceRepository userChoiceRepository;
    /**
     * userId가 답한 질문들에 대해,
     * '다수 선택'과 일치하는 답을 한 비율을 계산
     */
    @Transactional(readOnly = true)
    public GamePercentageResponseDto calculateUserFundamentalPercentage(Long userId) {
        // 1) userId가 답한 모든 SelectChoice 가져오기
        List<SelectChoice> userSelects = selectChoiceRepository.findAllByUserId(userId);

        // user가 답한 질문들만 모음
        List<Long> questionIds = userSelects.stream()
                .map(sc -> sc.getQuestion().getQuestionId()) // Question -> questionId
                .distinct()
                .collect(Collectors.toList());

        // 2) 각 질문별로 '가장 많이 선택된 choice' 찾기
        int fundamentalCount = 0; // 다수 선택과 일치하는 질문 수
        List<QuestionFundamentalDto> questionResults = new ArrayList<>();

        for (Long qId : questionIds) {
            // 해당 질문에 대해 전체 유저가 어떤 choice를 얼마나 골랐는지 count
            List<ChoiceCountDto> choiceCounts = selectChoiceRepository.countByQuestionIdGroupByChoiceId(qId);
            // choiceCounts: (choiceId, count)

            //가장 많이 선택된 choice들의 최대 count 찾기
            long maxCount = choiceCounts.stream()
                    .mapToLong(ChoiceCountDto::getCount)
                    .max()
                    .orElse(0);

            //maxCount와 같은 choiceId 목록
            List<Long> topChoiceIds = choiceCounts.stream()
                    .filter(c -> c.getCount() == maxCount)
                    .map(ChoiceCountDto::getChoiceId)
                    .collect(Collectors.toList());

            // (d) user가 이 질문에서 선택한 choiceId
            Long userChoiceId = userSelects.stream()
                    .filter(sc -> sc.getQuestion().equals(qId))
                    .map(SelectChoice::getChoiceId)
                    .findFirst()
                    .orElse(null);

            boolean isFundamental = (userChoiceId != null && topChoiceIds.contains(userChoiceId));
            if (isFundamental) {
                fundamentalCount++;
            }

            // 결과 DTO에 저장 (질문별로 isFundamental 여부를 알려주기 위함)
            questionResults.add(new QuestionFundamentalDto(qId, userChoiceId, isFundamental));
        }

        // 3) 퍼센트 계산
        int totalQuestions = questionIds.size();
        double percentage = 0.0;
        if (totalQuestions > 0) {
            percentage = (fundamentalCount / (double) totalQuestions) * 100.0;
        }

        // 4) 최종 응답 DTO 구성
        return new GamePercentageResponseDto(userId, percentage, questionResults);
    }
    @Transactional(readOnly = true)
    public CategoryResultResponse getCategoryResult(String categoryName) {
        // 1) 카테고리 찾기
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));

        // 2) 해당 카테고리에 속한 질문 목록 조회
        List<Question> questions = questionRepository.findByCategory(category);

        List<QuestionChoiceResult> resultList = new ArrayList<>();

        // 3) 각 질문에 대해 선택지별로 몇 명이 골랐는지 집계
        for (Question q : questions) {
            // (a) 질문을 답한 총 인원 수
            long totalAnswers = userChoiceRepository.countByQuestionId(q.getQuestionId());

            // (b) 질문의 선택지 목록
            List<SelectChoice> choices = selectChoiceRepository.findByQuestionId(q.getQuestionId());

            // (c) 각 선택지 count & percentage
            for (SelectChoice choice : choices) {
                long count = userChoiceRepository.countByQuestionIdAndChoiceId(q.getQuestionId(), choice.getChoiceId());

                double percentage = 0.0;
                if (totalAnswers > 0) {
                    percentage = (count * 100.0) / totalAnswers;
                }

                resultList.add(new QuestionChoiceResult(
                        q.getQuestionText(),
                        choice.getSelectedText(),
                        count,
                        percentage
                ));
            }
        }

        // 4) 응답 DTO
        return new CategoryResultResponse(category.getCategoryName(), resultList);
    }

    @Transactional(readOnly = true)
    public AllGameResultsResponse getAllGameResults() {
        // 1) 모든 질문 조회
        List<Question> allQuestions = questionRepository.findAll();

        // 2) 각 질문별로 선택지 목록 + 사용자 선택 수(vote) + 퍼센트 계산
        List<QuestionResultDto> questionResultList = new ArrayList<>();

        for (Question question : allQuestions) {
            // (a) 이 질문을 답한 총 인원 수
            long totalAnswers = userChoiceRepository.countByQuestionId(question.getQuestionId());

            // (b) 이 질문에 달린 모든 선택지 조회
            List<SelectChoice> choices = selectChoiceRepository.findByQuestionId(question.getQuestionId());

            // (c) 선택지별 vote, percentage 계산
            List<ChoiceResultDto> choiceResultList = new ArrayList<>();
            for (SelectChoice choice : choices) {
                // 몇 명이 이 선택지를 골랐는지
                long vote = userChoiceRepository.countByQuestionIdAndChoiceId(
                        question.getQuestionId(),
                        choice.getChoiceId()
                );

                double percentage = 0.0;
                if (totalAnswers > 0) {
                    percentage = (vote * 100.0) / totalAnswers;
                }

                choiceResultList.add(new ChoiceResultDto(
                        choice.getSelectedText(),  // selectchoice
                        percentage,                // percentage
                        vote                       // vote
                ));
            }

            // (d) 질문별 결과 DTO
            QuestionResultDto questionResult = new QuestionResultDto(
                    question.getQuestionText(),
                    choiceResultList
            );
            questionResultList.add(questionResult);
        }

        // 3) 최종 응답 객체 구성
        return new AllGameResultsResponse(questionResultList);
    }
}
