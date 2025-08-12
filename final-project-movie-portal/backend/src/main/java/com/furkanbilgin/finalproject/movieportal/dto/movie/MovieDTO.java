package com.furkanbilgin.finalproject.movieportal.dto.movie;

import com.furkanbilgin.finalproject.movieportal.dto.director.DirectorDTO;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
  private Long id;
  private String title;
  private String description;
  private DirectorDTO director;
  private Set<Long> categoryIds;
  private String releaseDate;
  private String genre;
  private String language;
  private String country;
  private String posterUrl;
}
