package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.user.RegisterUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO saveUser(RegisterUserDTO userDTO) {
        /*User user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.save(user));*/
        return null;
    }

    public List<UserDTO> findAllUsers() {
        return null;
        /*
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());*/
    }

    public UserDTO findUserById(Long id) {
        return null;
        /*return userRepository.findById(id).map(UserMapper::toDTO).orElse(null);*/
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {

        /*Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            // Update roles and courses as needed
            return UserMapper.toDTO(userRepository.save(user));
        }*/
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
