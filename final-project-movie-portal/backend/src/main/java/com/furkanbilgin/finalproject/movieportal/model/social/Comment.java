package com.furkanbilgin.finalproject.movieportal.model.social;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import com.furkanbilgin.finalproject.movieportal.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Comment extends BaseEntity {
    @OneToOne private User user;
    @OneToOne private Comment parent;
    @Column private String content;
}
