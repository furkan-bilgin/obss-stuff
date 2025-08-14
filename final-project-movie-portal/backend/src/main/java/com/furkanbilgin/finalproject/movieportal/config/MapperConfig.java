package com.furkanbilgin.finalproject.movieportal.config;

import com.furkanbilgin.finalproject.movieportal.dto.movie.MovieDTO;
import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    modelMapper
        .typeMap(MovieDTO.class, Movie.class)
        .addMappings(mapper -> mapper.skip(Movie::setCategories));
    return modelMapper;
  }
}
