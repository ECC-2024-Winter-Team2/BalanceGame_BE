package com.ecc.balancegame.service;

import com.ecc.balancegame.domain.Choice;
import com.ecc.balancegame.dto.ChoiceRequestDto;
import com.ecc.balancegame.repository.ChoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChoiceService {
    private final ChoiceRepository choiceRepository;

    public ChoiceService(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    @Transactional
    public void saveChoices(ChoiceRequestDto choiceRequestDto) {
        List<Choice> choices = choiceRequestDto.getSelections().stream()
                .map(selection -> new Choice(
                        choiceRequestDto.getUserId(),
                        choiceRequestDto.getCategoryId(),
                        selection.getQuestionId(),
                        selection.getChoiceId()
                ))
                .collect(Collectors.toList());

        choiceRepository.saveAll(choices); // 🟢 Batch Insert 적용
    }
}
