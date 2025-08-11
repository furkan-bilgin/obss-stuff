package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import com.furkanbilgin.finalproject.movieportal.model.user.User;
import com.furkanbilgin.finalproject.movieportal.model.user.UserMovieFavorite;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserMovieFavoriteRepository extends JpaRepository<UserMovieFavorite, Long> {
  @Query("SELECT umf FROM UserMovieFavorite umf WHERE umf.user = ?1 AND umf.movie = ?2")
  Optional<UserMovieFavorite> findByUserAndMovie(User user, Movie movie);
}
