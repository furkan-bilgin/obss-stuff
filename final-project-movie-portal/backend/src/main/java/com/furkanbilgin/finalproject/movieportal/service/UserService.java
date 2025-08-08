package com.furkanbilgin.finalproject.movieportal.service;

import com.furkanbilgin.finalproject.movieportal.dto.CourseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.RegisterUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(RegisterUserDTO userDTO);

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    List<CourseDTO> getMyCourses(String username);
}
