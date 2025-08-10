package com.furkanbilgin.finalproject.movieportal.dto.user.favorite;

import jakarta.validation.constraints.NotBlank;

public record UserUnfavoriteMovieDTO(@NotBlank Long movieId) {}
