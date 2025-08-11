package com.furkanbilgin.finalproject.movieportal.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration()
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
        .setSkipNullEnabled(true)
        .setSourceNamingConvention(org.modelmapper.convention.NamingConventions.JAVABEANS_ACCESSOR)
        .setDestinationNamingConvention(
            org.modelmapper.convention.NamingConventions.JAVABEANS_ACCESSOR);
    return modelMapper;
  }
}
