package com.ecc.balancegame.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 보호
@AllArgsConstructor
@Builder
@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long categoryId;
    private Long questionId;
    private Long choiceId;
    private Integer selectedNumber;

    // 🟢 생성자로 값 설정 (Setter 없이 객체 생성)
    public Choice(Long userId, Long categoryId, Long questionId, Long choiceId, Integer selectedNumber) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.selectedNumber = selectedNumber;
    }
}
