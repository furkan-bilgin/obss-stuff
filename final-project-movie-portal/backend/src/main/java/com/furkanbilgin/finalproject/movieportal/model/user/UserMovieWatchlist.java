package com.furkanbilgin.finalproject.movieportal.model.user;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Getter;

@Entity
@Getter
public class UserMovieWatchlist extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
