package com.ecc.balancegame.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDeleteDto {
    private Long commentId;
    private String password;
}