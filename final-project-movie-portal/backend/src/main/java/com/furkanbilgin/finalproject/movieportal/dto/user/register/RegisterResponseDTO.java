package com.furkanbilgin.finalproject.movieportal.dto.user.register;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
  private UserDTO userDTO;
}
