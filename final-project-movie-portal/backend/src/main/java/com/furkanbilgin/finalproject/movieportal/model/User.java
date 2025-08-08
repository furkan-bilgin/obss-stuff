package com.furkanbilgin.finalproject.movieportal.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
@Data
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable()
    @Builder.Default
    @Column
    private List<Role> roles = List.of();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
}
