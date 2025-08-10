package com.furkanbilgin.finalproject.movieportal.dto.user.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
    @NotBlank(message = "Username cannot be blank") String username,
    @Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank")
        String email,
    @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
        String password) {}
