package com.ecc.balancegame.domain;

import com.ecc.balancegame.domain.Category;
import com.ecc.balancegame.domain.Question;
import com.ecc.balancegame.domain.SelectChoice;
import com.ecc.balancegame.domain.User;
import jakarta.persistence.*;
import lombok.*;


/**
 * "어떤 유저가, 어떤 카테고리의, 어떤 질문에 대해,
 *  어떤 선택지(SelectChoice)를 골랐는가" 기록하는 테이블
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_choice")
public class UserChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_id")
    private Long userChoiceId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;            // 어떤 유저가

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;    // 어떤 카테고리인지

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;    // 어떤 질문에 대해

    @ManyToOne
    @JoinColumn(name = "choice_id", nullable = false)
    private SelectChoice choice;  // 어떤 선택지를 골랐는지

}
