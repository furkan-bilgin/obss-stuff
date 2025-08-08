package com.furkanbilgin.finalproject.movieportal.model.user;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import com.furkanbilgin.finalproject.movieportal.model.Role;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuperBuilder(toBuilder = true)
@Entity
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable()
    @Builder.Default
    @Column
    private List<Role> roles = List.of();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserMovieFavorite> favorites = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserMovieWatchlist> watchlist = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
