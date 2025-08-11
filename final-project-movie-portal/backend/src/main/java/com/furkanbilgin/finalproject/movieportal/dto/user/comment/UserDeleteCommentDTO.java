package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDeleteCommentDTO {
  @NotBlank private Long commentId;
}
