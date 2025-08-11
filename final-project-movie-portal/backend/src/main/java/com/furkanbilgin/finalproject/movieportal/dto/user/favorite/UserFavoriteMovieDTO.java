package com.furkanbilgin.finalproject.movieportal.dto.user.favorite;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFavoriteMovieDTO {
  private Long movieId;
  private Long score;
}
