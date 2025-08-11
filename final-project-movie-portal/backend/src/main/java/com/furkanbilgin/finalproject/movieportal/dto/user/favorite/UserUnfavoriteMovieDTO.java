package com.furkanbilgin.finalproject.movieportal.dto.user.favorite;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUnfavoriteMovieDTO {
  @NotBlank private Long movieId;
}
