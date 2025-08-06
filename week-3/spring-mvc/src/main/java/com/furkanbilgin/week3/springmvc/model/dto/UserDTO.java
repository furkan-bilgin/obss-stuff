package com.furkanbilgin.week3.springmvc.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @Size(min = 3, message = "Surname must be at least 3 characters long")
    private String surname;

    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 20, message = "Age must be at least 20")
    private Integer age;
}
