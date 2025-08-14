package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.user.login.LoginRequestDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.login.LoginResponseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.register.RegisterRequestDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.register.RegisterResponseDTO;
import com.furkanbilgin.finalproject.movieportal.exception.InvalidCredentialsException;
import com.furkanbilgin.finalproject.movieportal.exception.UserAlreadyExistsException;
import com.furkanbilgin.finalproject.movieportal.exception.UserNotFoundException;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public AuthController(AuthenticationManager authenticationManager, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginUserDTO) {
    try {
      var auth =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginUserDTO.getUsername(), loginUserDTO.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(auth);
      var user = userService.findUserByUsername(auth.getName());
      if (user.isEmpty()) {
        throw new UserNotFoundException("User not found");
      }
      var token = userService.updateUserToken(user.get().getId());
      return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
    } catch (Exception e) {
      throw new InvalidCredentialsException("Invalid username or password");
    }
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponseDTO> register(
      @RequestBody RegisterRequestDTO registerUserDTO) {
    if (userService.findUserByUsername(registerUserDTO.getUsername()).isPresent()) {
      throw new UserAlreadyExistsException("Username already exists");
    }
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new RegisterResponseDTO(userService.saveUser(registerUserDTO)));
  }
}
