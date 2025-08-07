package com.furkanbilgin.week3.student.controller;

import com.furkanbilgin.week3.student.dto.CourseDTO;
import com.furkanbilgin.week3.student.dto.RegisterUserDTO;
import com.furkanbilgin.week3.student.dto.UserDTO;
import com.furkanbilgin.week3.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/me/courses")
    public List<CourseDTO> getMyCourses(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getMyCourses(userDetails.getUsername());
    }
}
