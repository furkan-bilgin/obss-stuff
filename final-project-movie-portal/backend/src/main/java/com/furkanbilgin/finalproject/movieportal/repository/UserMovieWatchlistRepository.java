package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import com.furkanbilgin.finalproject.movieportal.model.user.User;
import com.furkanbilgin.finalproject.movieportal.model.user.UserMovieWatchlist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserMovieWatchlistRepository extends JpaRepository<UserMovieWatchlist, Long> {
  @Query("SELECT umw FROM UserMovieWatchlist umw WHERE umw.user = ?1 AND umw.movie = ?2")
  Optional<UserMovieWatchlist> findByUserAndMovie(User user, Movie movie);
}
