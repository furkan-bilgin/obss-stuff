package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.imdbApi.FetchTitlesResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.imdbApi.TitleDTO;
import com.furkanbilgin.finalproject.movieportal.service.MovieService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/imdb")
@RequiredArgsConstructor
public class IMDBAPIController {

  private static final String IMDB_API_GET_TITLES_URL = "https://api.imdbapi.dev/titles";
  private final MovieService movieService;
  private final RestTemplate restTemplate;

  @Autowired
  public IMDBAPIController(MovieService movieService) {
    this.movieService = movieService;
    this.restTemplate = new RestTemplate();
  }

  @PostMapping("/fetch-titles")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<FetchTitlesResponseDTO> fetchAndSaveTitles() {
    var headers = new HttpHeaders();
    var entity = new HttpEntity<String>(headers);

    var response =
        restTemplate.exchange(IMDB_API_GET_TITLES_URL, HttpMethod.GET, entity, TitleListDTO.class);

    if (response.getBody() != null && response.getBody().titles != null) {
      var movieDTOs =
          response.getBody().titles.stream().map(movieService::saveMovieFromIMDBTitleDTO).toList();
      return ResponseEntity.ok(new FetchTitlesResponseDTO(movieDTOs));
    }

    throw new RuntimeException("Failed to fetch titles from IMDB API");
  }

  private static class TitleListDTO {
    public List<TitleDTO> titles;
  }
}
