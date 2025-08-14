package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
  @Query("SELECT m FROM Movie m WHERE m.title = ?1")
  Optional<Movie> findByTitle(String title);

  @Query(
      "SELECT DISTINCT m FROM Movie m LEFT JOIN m.categories c "
          + "WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', ?1, '%'))"
          + "OR LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
  List<Movie> search(String title);
}
