package com.furkanbilgin.finalproject.movieportal.model.social;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import com.furkanbilgin.finalproject.movieportal.model.movie.Movie;
import com.furkanbilgin.finalproject.movieportal.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity {
  @ManyToOne private User user;

  @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
  private Comment parent;

  @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
  private Movie movie;

  @Column private String content;
}
