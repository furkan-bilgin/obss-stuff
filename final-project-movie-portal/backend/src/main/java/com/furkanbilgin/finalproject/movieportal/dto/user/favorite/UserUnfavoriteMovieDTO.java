package com.furkanbilgin.finalproject.movieportal.dto.user.favorite;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUnfavoriteMovieDTO {
  @NotNull
  private Long movieId;
}
