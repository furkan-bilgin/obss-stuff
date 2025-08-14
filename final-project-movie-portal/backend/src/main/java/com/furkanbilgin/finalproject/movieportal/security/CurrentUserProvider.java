package com.furkanbilgin.finalproject.movieportal.security;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserProvider {
  private final UserService userService;

  public CurrentUserProvider(UserService userService) {
    this.userService = userService;
  }

  public UserDTO getCurrentUser() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    // Probably don't even need this because security filter handles this
    // before it even reaches us, but well...
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new AccessDeniedException("Not authorized");
    }
    var username = authentication.getName();
    var user = userService.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new AccessDeniedException("User not found");
    }
    return user.get();
  }
}
