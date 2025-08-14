package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
    MovieDTO savedMovie = movieService.saveMovie(movieDTO);
    return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<MovieDTO>> getAllMovies() {
    List<MovieDTO> movies = movieService.getAllMovies();
    return ResponseEntity.ok(movies);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
    return movieService
        .getMovieById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<MovieDTO> updateMovie(
      @PathVariable Long id, @RequestBody MovieDTO movieDTO) {
    return movieService
        .updateMovie(id, movieDTO)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void deleteMovie(@PathVariable Long id) {
    movieService.deleteMovieById(id);
  }

  @GetMapping("/search")
  public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam String query) {
    var movies = movieService.searchMovies(query);
    return ResponseEntity.ok(movies);
  }
}
