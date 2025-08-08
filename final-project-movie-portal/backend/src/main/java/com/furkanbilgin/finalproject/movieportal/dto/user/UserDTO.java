package com.furkanbilgin.finalproject.movieportal.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private List<Long> courseIds;
}
