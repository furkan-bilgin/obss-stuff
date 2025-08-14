package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.imdbApi.TitleDTO;
import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Category;
import com.furkanbilgin.finalproject.movieportal.model.movie.Director;
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
  @Transactional
  public MovieDTO saveMovieFromIMDBTitleDTO(TitleDTO titleDTO) {
    var dbMovie = movieRepository.findByTitle(titleDTO.getPrimaryTitle());
    var movie = dbMovie.orElseGet(Movie::new);

    movie.setTitle(titleDTO.getPrimaryTitle());
    movie.setDescription(titleDTO.getPlot());
    movie.setReleaseDate(String.valueOf(titleDTO.getStartYear()));
    movie.setRuntime(titleDTO.getRuntimeSeconds() / 60);

    if (titleDTO.getSpokenLanguages() != null && !titleDTO.getSpokenLanguages().isEmpty()) {
      movie.setLanguage(titleDTO.getSpokenLanguages().getFirst().getName());
    }

    if (titleDTO.getOriginCountries() != null && !titleDTO.getOriginCountries().isEmpty()) {
      movie.setCountry(titleDTO.getOriginCountries().getFirst().getName());
    }

    if (titleDTO.getPrimaryImage() != null) {
      movie.setPosterUrl(titleDTO.getPrimaryImage().getUrl());
    }

    if (titleDTO.getDirectors() != null && !titleDTO.getDirectors().isEmpty()) {
      var directorDto = titleDTO.getDirectors().getFirst();
      var director =
          directorRepository
              .findByName(directorDto.getDisplayName())
              .orElseGet(
                  () -> {
                    Director newDirector = new Director();
                    newDirector.setName(directorDto.getDisplayName());
                    return directorRepository.save(newDirector);
                  });
      movie.setDirector(director);
    }

    if (titleDTO.getGenres() != null && !titleDTO.getGenres().isEmpty()) {
      var categories =
          titleDTO.getGenres().stream()
              .map(
                  genreName ->
                      categoryRepository
                          .findByName(genreName)
                          .orElseGet(
                              () -> {
                                Category newCategory = new Category();
                                newCategory.setName(genreName);
                                return categoryRepository.save(newCategory);
                              }))
              .collect(Collectors.toList());
      movie.setCategories(categories);
    }

    if (titleDTO.getMetacritic() != null) {
      movie.setMetacriticRating((float) titleDTO.getMetacritic().getScore());
    }

    if (titleDTO.getRating() != null) {
      movie.setImdbRating((float) titleDTO.getRating().getAggregateRating());
    }

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

  @Override
  public List<MovieDTO> searchMovies(String query) {
    return movieRepository.searchByTitle(query).stream()
        .map(movie -> modelMapper.map(movie, MovieDTO.class))
        .collect(Collectors.toList());
  }

  private void updateMovieRelations(Movie movie, MovieDTO movieDTO) {
    if (movieDTO.getDirector() == null) {
      movie.setDirector(null);
    } else if (movieDTO.getDirector().getId() != null) {
      var director =
          directorRepository
              .findById(movieDTO.getDirector().getId())
              .orElseThrow(() -> new RuntimeException("Director not found"));
      movie.setDirector(director);
    }

    if (movieDTO.getCategories() != null && !movieDTO.getCategories().isEmpty()) {
      var categories =
          movieDTO.getCategories().stream()
              .map(
                  cat ->
                      categoryRepository
                          .findById(cat.getId())
                          .orElseThrow(() -> new RuntimeException("Category not found")))
              .collect(Collectors.toList());
      movie.setCategories(categories);
    }
  }
}
