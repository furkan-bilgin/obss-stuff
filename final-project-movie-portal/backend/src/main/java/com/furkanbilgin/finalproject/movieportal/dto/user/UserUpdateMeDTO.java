package com.furkanbilgin.finalproject.movieportal.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateMeDTO {
  @Email(message = "Email should be valid")
  private String email;

  @Pattern(regexp = "^$|.{8,20}", message = "Password must be between 8 and 20 characters")
  private String password;
}
