package com.furkanbilgin.finalproject.movieportal.dto.user.refreshToken;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshAccessTokenResponseDTO {
  private String accessToken;
}
