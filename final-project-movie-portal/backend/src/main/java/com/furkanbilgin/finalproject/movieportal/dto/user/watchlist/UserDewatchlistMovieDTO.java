package com.furkanbilgin.finalproject.movieportal.dto.user.watchlist;

import jakarta.validation.constraints.NotBlank;

public record UserDewatchlistMovieDTO(@NotBlank Long movieId) {}
