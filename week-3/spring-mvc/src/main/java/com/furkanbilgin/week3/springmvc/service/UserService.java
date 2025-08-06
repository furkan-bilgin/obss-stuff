package com.furkanbilgin.week3.springmvc.service;

import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    List<UserDTO> findAllUsers();

    UserDTO findUserById(Long id);

    void deleteUser(Long id);
}
