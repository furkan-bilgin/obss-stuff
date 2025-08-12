package com.furkanbilgin.finalproject.movieportal.dto.imdbApi;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchTitlesResponseDTO {
  private List<MovieDTO> titles;
}
