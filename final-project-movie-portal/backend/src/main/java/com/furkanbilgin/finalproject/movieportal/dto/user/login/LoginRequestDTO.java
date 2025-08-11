package com.furkanbilgin.finalproject.movieportal.dto.user.login;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDTO {
  @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
  private String username;

  @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
  private String password;
}
