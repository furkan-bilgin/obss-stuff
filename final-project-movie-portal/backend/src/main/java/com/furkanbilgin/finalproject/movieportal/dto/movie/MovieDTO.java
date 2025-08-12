package com.furkanbilgin.finalproject.movieportal.dto.movie;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
  private Long id;
  private String title;
  private String description;
  private DirectorDTO director;
  private List<CategoryDTO> categories;
  private String releaseDate;
  private String language;
  private String country;
  private String posterUrl;
  private Float metacriticRating;
  private Float imdbRating;
  private Integer runtime;
}
