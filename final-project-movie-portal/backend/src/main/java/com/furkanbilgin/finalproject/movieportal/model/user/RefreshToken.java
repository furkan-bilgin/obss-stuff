package com.furkanbilgin.finalproject.movieportal.model.user;

import com.furkanbilgin.finalproject.movieportal.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken extends BaseEntity {
  @ManyToOne private User user;
  private String token;
  private LocalDateTime expiresAt;
}
