package com.furkanbilgin.finalproject.movieportal.dto.user;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMovieWatchlistDTO {
  private UserDTO user;
  private List<MovieDTO> watchlist;
}
