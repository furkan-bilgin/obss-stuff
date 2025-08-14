package com.furkanbilgin.finalproject.movieportal.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateMeDTO {
  private String email;
  private String password;
}
