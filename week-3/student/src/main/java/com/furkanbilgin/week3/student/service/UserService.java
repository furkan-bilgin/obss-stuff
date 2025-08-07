package com.furkanbilgin.week3.student.service;

import com.furkanbilgin.week3.student.dto.CourseDTO;
import com.furkanbilgin.week3.student.dto.RegisterUserDTO;
import com.furkanbilgin.week3.student.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO saveUser(RegisterUserDTO userDTO);

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

    List<CourseDTO> getMyCourses(String username);
}
