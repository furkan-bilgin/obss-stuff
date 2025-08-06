package com.furkanbilgin.week3.springmvc.model.dto;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;
}
