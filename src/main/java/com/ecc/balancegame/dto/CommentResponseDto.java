package com.ecc.balancegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private String username;             // 댓글 작성자 닉네임
    private String content;              // 댓글 내용
    private LocalDateTime createdAt;     // 작성 시각
    private int likes;                   // 좋아요(추천) 수
}
