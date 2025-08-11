package com.furkanbilgin.finalproject.movieportal.dto.user.watchlist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUnwatchlistMovieDTO {
  @NotNull
  private Long movieId;
}
