package com.furkanbilgin.finalproject.movieportal.repository;

import com.furkanbilgin.finalproject.movieportal.model.user.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> getRefreshTokenByToken(String token);

  @Query("SELECT r FROM RefreshToken r WHERE r.user.id = ?1")
  Optional<RefreshToken> getRefreshTokenByUserId(Long userId);
}
