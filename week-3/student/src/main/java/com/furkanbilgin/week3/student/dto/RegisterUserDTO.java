package com.furkanbilgin.week3.student.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDTO {
    private String username;
    private String email;
    private String password;
}
