package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentCreateDto {
    private String status;
    private String message;
    private Long commentId;
}
