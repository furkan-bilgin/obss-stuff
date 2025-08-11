package com.furkanbilgin.finalproject.movieportal.dto.user.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCommentDTO {
  private Long parentCommentId;

  @NotBlank
  @Size(min = 1, max = 1000)
  private String content;
}
