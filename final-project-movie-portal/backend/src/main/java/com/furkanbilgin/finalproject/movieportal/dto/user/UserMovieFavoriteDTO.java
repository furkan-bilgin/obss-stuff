package com.furkanbilgin.finalproject.movieportal.dto.user;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMovieFavoriteDTO {
  private UserDTO user;
  private List<MovieScorePairDTO> favorites;

  @Data
  @AllArgsConstructor
  public static class MovieScorePairDTO {
    private MovieDTO movie;
    private Integer score;
  }
}
