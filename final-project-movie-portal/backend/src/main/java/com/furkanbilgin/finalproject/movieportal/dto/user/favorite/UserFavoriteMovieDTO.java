package com.furkanbilgin.finalproject.movieportal.dto.user.favorite;

import lombok.Data;

@Data
public class UserFavoriteMovieDTO {
    private Long movieId;
    private Long score = null;
}
