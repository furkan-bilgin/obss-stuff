package com.furkanbilgin.finalproject.movieportal.model.movie;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Director extends BaseEntity {
    @Column private String name;
    @Column private String biography;
    @Column private LocalDateTime birthDate;

    @OneToMany(mappedBy = "director")
    private List<Movie> moviesDirected;
}
