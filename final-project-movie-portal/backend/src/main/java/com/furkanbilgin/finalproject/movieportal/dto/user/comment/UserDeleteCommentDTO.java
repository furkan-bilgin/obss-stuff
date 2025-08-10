package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import jakarta.validation.constraints.NotBlank;

public record UserDeleteCommentDTO(@NotBlank Long commentId) {}
