package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import lombok.Data;

@Data
public class UserCommentDTO {
    private Long parentCommentId;
    private String content;
}
