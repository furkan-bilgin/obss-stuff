package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.movie.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  @Query("SELECT c FROM Category c WHERE c.name = ?1")
  Optional<Category> findByName(String name);
}
