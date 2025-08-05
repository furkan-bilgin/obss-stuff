package com.furkanbilgin.week3.springmvc.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

public @Data class UserRegisterForm {
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Size(min = 3, message = "Surname must be at least 3 characters long")
    private String surname;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 20, message = "Age must be at least 20")
    private int age;
}
