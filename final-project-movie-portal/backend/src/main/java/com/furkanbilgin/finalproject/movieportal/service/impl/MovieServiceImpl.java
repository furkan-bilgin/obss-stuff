package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import com.furkanbilgin.finalproject.movieportal.repository.CategoryRepository;
import com.furkanbilgin.finalproject.movieportal.repository.DirectorRepository;
import com.furkanbilgin.finalproject.movieportal.repository.MovieRepository;
import com.furkanbilgin.finalproject.movieportal.service.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final DirectorRepository directorRepository;
  private final CategoryRepository categoryRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public MovieDTO saveMovie(MovieDTO movieDTO) {
    var movie = modelMapper.map(movieDTO, Movie.class);
    updateMovieRelations(movie, movieDTO);
    var savedMovie = movieRepository.save(movie);
    return modelMapper.map(savedMovie, MovieDTO.class);
  }

  @Override
  public List<MovieDTO> getAllMovies() {
    return movieRepository.findAll().stream()
        .map(movie -> modelMapper.map(movie, MovieDTO.class))
        .collect(Collectors.toList());
  }

  @Override
  public Optional<MovieDTO> getMovieById(Long id) {
    return movieRepository.findById(id).map(movie -> modelMapper.map(movie, MovieDTO.class));
  }

  @Override
  public void deleteMovieById(Long id) {
    movieRepository.deleteById(id);
  }

  @Override
  @Transactional
  public Optional<MovieDTO> updateMovie(Long id, MovieDTO movieDTO) {
    return movieRepository
        .findById(id)
        .map(
            existingMovie -> {
              modelMapper.map(movieDTO, existingMovie);
              updateMovieRelations(existingMovie, movieDTO);
              Movie updatedMovie = movieRepository.save(existingMovie);
              return modelMapper.map(updatedMovie, MovieDTO.class);
            });
  }

  private void updateMovieRelations(Movie movie, MovieDTO movieDTO) {
    if (movieDTO.getDirector().getId() != null) {
      var director =
          directorRepository
              .findById(movieDTO.getDirector().getId())
              .orElseThrow(() -> new RuntimeException("Director not found"));
      movie.setDirector(director);
    }

    if (movieDTO.getCategoryIds() != null && !movieDTO.getCategoryIds().isEmpty()) {
      var categories =
          movieDTO.getCategoryIds().stream()
              .map(
                  catId ->
                      categoryRepository
                          .findById(catId)
                          .orElseThrow(() -> new RuntimeException("Category not found")))
              .collect(Collectors.toSet());
      movie.setCategories(categories);
    }
  }
}
