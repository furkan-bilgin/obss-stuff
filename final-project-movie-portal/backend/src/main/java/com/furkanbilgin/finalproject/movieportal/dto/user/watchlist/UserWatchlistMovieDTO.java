package com.furkanbilgin.finalproject.movieportal.dto.user.watchlist;

import jakarta.validation.constraints.NotBlank;

public record UserWatchlistMovieDTO(@NotBlank Long movieId) {}
