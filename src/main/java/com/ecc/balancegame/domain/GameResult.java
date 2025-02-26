package com.ecc.balancegame.domain;
import jakarta.persistence.*;
import lombok.*;

/**
 * 게임 결과 테이블
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "game_result")
public class GameResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    // 어떤 유저의 결과인지
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 어떤 카테고리에 대한 결과인지
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // 어떤 질문에 대한 결과인지 (만약 게임 결과가 질문별로 저장된다면)
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    // ERD에 "근본인가 (isFundamental)"라고 되어 있으면
    // 다수 선택과 일치하는지 여부를 저장하는 용도인 듯
    @Column(name = "is_fundamental")
    private Boolean isFundamental;
}