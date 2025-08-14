package com.furkanbilgin.finalproject.movieportal.model.movie;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Director extends BaseEntity {
  private String name;

  @OneToMany(
      mappedBy = "director",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Movie> movies = new ArrayList<>();

  @PreRemove
  private void removeDirectorFromMovies() {
    for (var movie : movies) {
      movie.setDirector(null);
    }
  }
}
