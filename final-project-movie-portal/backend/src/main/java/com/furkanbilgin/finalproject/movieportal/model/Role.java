package com.furkanbilgin.finalproject.movieportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;

@SuperBuilder(toBuilder = true)
@Entity
@NoArgsConstructor
@Getter
public class Role extends BaseEntity implements GrantedAuthority {
    @Column private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
