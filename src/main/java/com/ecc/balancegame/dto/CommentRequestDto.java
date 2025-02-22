package com.ecc.balancegame.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentRequestDto {
    private Long categoryId;
    private Long questionId;
    private String userName;
    private String password;
    private String content;
    private boolean anonymous;
}
