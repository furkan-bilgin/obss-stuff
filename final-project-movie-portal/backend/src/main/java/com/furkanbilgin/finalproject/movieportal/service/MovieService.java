package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.imdbApi.TitleDTO;
import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import java.util.List;
import java.util.Optional;

public interface MovieService {
  MovieDTO saveMovie(MovieDTO movieDTO);

  MovieDTO saveMovieFromIMDBTitleDTO(TitleDTO titleDTO);

  List<MovieDTO> getAllMovies();

  Optional<MovieDTO> getMovieById(Long id);

  void deleteMovieById(Long id);

  Optional<MovieDTO> updateMovie(Long id, MovieDTO movieDTO);
}
