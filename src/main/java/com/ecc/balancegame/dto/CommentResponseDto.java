package com.ecc.balancegame.dto;

import com.ecc.balancegame.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private String userName;
    private String content;
    private boolean anonymous;
    private LocalDateTime createdAt;
    private int likes;

    public static CommentResponseDto fromEntity(Comment comment){
        return new CommentResponseDto(
                comment.getUser().getUserName(),
                comment.getContent(),
                comment.isAnonymous(),
                comment.getCreatedAt(),
                comment.getLikes()
        );
    }
}
