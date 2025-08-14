package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import java.util.Optional;

public interface RefreshTokenService {
  String generateRefreshToken(Long userId);

  Optional<UserDTO> getUserFromRefreshToken(String refreshToken);

  void invalidateRefreshToken(String token);
}
