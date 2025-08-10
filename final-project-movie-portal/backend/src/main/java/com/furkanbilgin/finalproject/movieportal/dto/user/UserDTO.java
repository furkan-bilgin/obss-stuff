package com.furkanbilgin.finalproject.movieportal.dto.user;

import java.util.List;

public record UserDTO(Long id, String username, String email, List<String> roles) {}
