package com.furkanbilgin.finalproject.movieportal.controller;

import com.furkanbilgin.finalproject.movieportal.dto.user.RegisterUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public UserDTO registerUser(@ModelAttribute("user") RegisterUserDTO registerUserDTO) {
        return userService.saveUser(registerUserDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'LECTURER')")
    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
