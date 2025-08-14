package com.furkanbilgin.finalproject.movieportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
  @Column private String name;

  @Override
  public String getAuthority() {
    return name;
  }
}
