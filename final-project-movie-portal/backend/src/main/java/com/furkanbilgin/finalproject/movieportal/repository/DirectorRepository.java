package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.movie.Director;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
  @Query("SELECT d FROM Director d WHERE d.name = ?1")
  Optional<Director> findByName(String name);
}
