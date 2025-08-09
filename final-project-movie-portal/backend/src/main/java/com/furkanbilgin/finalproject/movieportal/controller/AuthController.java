package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.user.LoginUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.RegisterUserDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginUserDTO loginUserDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDTO.getUsername(), loginUserDTO.getPassword()));
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserDTO registerUserDTO) {}
}
