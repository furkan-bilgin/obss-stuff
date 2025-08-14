package com.furkanbilgin.finalproject.movieportal.model.movie;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreRemove;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie extends BaseEntity {
  @Column private String title;
  @Column private String description;

  @ManyToOne
  @JoinTable(
      name = "movie_director",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "director_id"))
  private Director director;

  @ManyToMany(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "movie_categories",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories = new ArrayList<>();

  @Column private String releaseDate;
  @Column private String language;
  @Column private String country;
  @Column private String posterUrl;
  @Column private Float metacriticRating;
  @Column private Float imdbRating;
  @Column private Integer runtime; // in minutes

  @PreRemove
  private void removeMovieFromAssociations() {
    if (director != null) {
      director.getMovies().remove(this);
    }
    for (var category : new ArrayList<>(categories)) {
      category.getMovies().remove(this);
    }
  }
}
