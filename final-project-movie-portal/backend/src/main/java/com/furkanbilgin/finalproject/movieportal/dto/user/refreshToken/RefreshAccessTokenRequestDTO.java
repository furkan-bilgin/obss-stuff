package com.furkanbilgin.finalproject.movieportal.dto.user.refreshToken;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshAccessTokenRequestDTO {
  private String refreshToken;
}
