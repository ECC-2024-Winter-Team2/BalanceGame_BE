package com.ecc.balancegame.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentUpdateDto {
    private Long commentId;
    private String password;
    private String content;
}