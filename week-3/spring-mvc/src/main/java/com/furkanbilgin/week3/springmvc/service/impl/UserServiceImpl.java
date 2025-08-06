package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.component.UserMapper;
import com.furkanbilgin.week3.springmvc.exception.UserNotFoundException;
import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;
import com.furkanbilgin.week3.springmvc.repository.UserRepository;
import com.furkanbilgin.week3.springmvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        var user = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        userRepository.updateUser(id, userDTO);
        return findUserById(id);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO findUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
    }
}
