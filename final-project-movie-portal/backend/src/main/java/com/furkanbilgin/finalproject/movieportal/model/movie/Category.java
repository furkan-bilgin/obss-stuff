package com.furkanbilgin.finalproject.movieportal.model.movie;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import lombok.Getter;

import java.util.Set;

@Entity
@Getter
public class Category extends BaseEntity {
    @Column private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Movie> movies;
}
