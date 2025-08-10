package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCommentDTO(
    Long parentCommentId, @NotBlank @Size(min = 1, max = 1000) String content) {}
