package com.furkanbilgin.finalproject.movieportal.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
}
