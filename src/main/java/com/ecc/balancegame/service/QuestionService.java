package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.dto.ChoiceResponseDto;
import com.ecc.balancegame.dto.QuestionResponseDto;
import com.ecc.balancegame.repository.QuestionRepository;
import com.ecc.balancegame.repository.SelectChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final SelectChoiceRepository selectChoiceRepository;

    @Transactional(readOnly = true)
    public List<QuestionResponseDto> getQuestionsByCategory(Long categoryId) {

        List<Question> questions = questionRepository.findByCategoryIdOrderByOrderIndex(categoryId);

        return questions.stream().map(question -> {

            List<SelectChoice> choices = selectChoiceRepository.findByQuestion(question);

            return new QuestionResponseDto(
                    question.getQuestionId(),
                    question.getQuestionText(),
                    question.getCategory().getCategoryId(),
                    question.getOrderIndex(),
                    choices.stream().map(choice -> new ChoiceResponseDto(
                            choice.getChoiceId(),
                            choice.getChoiceNumber(),
                            choice.getChoiceText()
                    )).collect(Collectors.toList())
            );
        }).collect(Collectors.toList());
    }



}
