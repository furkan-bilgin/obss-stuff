package com.furkanbilgin.week3.student.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Entity
@Data
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
    @Column private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
