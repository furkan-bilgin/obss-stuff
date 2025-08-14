package com.furkanbilgin.finalproject.movieportal.config;

import com.furkanbilgin.finalproject.movieportal.model.Role;
import com.furkanbilgin.finalproject.movieportal.model.SystemRole;
import com.furkanbilgin.finalproject.movieportal.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer {
  private final RoleRepository roleRepository;

  @PostConstruct
  public void initRoles() {
    for (var systemRole : SystemRole.values()) {
      roleRepository
          .findByName(systemRole.name())
          .orElseGet(() -> roleRepository.save(new Role(systemRole.name())));
    }
  }
}
