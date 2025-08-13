package com.furkanbilgin.finalproject.movieportal.model.user;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import com.furkanbilgin.finalproject.movieportal.model.Role;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<UserMovieFavorite> favorites = new ArrayList<>();

  @OneToMany(
      mappedBy = "user",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  private List<UserMovieWatchlist> watchlist = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }
}
