package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.model.user.RefreshToken;
import com.furkanbilgin.finalproject.movieportal.repository.RefreshTokenRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.RefreshTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;
  private final ModelMapper modelMapper;
  private final UserRepository userRepository;

  public RefreshTokenServiceImpl(
      RefreshTokenRepository refreshTokenRepository,
      ModelMapper modelMapper,
      UserRepository userRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
    this.modelMapper = modelMapper;
    this.userRepository = userRepository;
  }

  @Override
  public String generateRefreshToken(Long userId) {
    var existingToken = refreshTokenRepository.getRefreshTokenByUserId(userId);
    if (existingToken.isPresent()) {
      return existingToken.get().getToken();
    }
    var user = userRepository.findById(userId).orElseThrow();
    var refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setUser(user);
    refreshTokenRepository.save(refreshToken);

    return refreshToken.getToken();
  }

  @Override
  public Optional<UserDTO> getUserFromRefreshToken(String token) {
    var refreshToken = refreshTokenRepository.getRefreshTokenByToken(token);
    return refreshToken.map(
        value -> {
          if (value.getExpiresAt() != null && value.getExpiresAt().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(value);
            throw new IllegalArgumentException("Refresh token has expired");
          }
          return modelMapper.map(value.getUser(), UserDTO.class);
        });
  }

  @Override
  public void invalidateRefreshToken(String token) {
    var refreshToken = refreshTokenRepository.getRefreshTokenByToken(token);
    if (refreshToken.isPresent()) {
      refreshTokenRepository.delete(refreshToken.get());
    } else {
      throw new IllegalArgumentException("Invalid refresh token");
    }
  }
}
