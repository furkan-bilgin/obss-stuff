package com.furkanbilgin.finalproject.movieportal.dto.user.watchlist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserWatchlistMovieDTO {
  @NotBlank private Long movieId;
}
