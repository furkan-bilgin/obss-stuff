package com.furkanbilgin.finalproject.movieportal.model.movie;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PreRemove;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseEntity {
  private String name;

  @ManyToMany(
      mappedBy = "categories",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Movie> movies = new ArrayList<>();

  @PreRemove
  private void removeCategoriesFromMovies() {
    for (var movie : movies) {
      movie.getCategories().remove(this);
    }
  }
}
