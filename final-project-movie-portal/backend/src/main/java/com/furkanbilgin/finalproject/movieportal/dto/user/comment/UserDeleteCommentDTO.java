package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDeleteCommentDTO {
  @NotNull private Long commentId;
}
