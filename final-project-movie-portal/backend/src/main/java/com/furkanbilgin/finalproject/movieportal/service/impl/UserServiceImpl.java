package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.register.RegisterRequestDTO;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.UserService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public UserDTO saveUser(RegisterRequestDTO registerUserDTO) {
    /*User user = UserMapper.toEntity(userDTO);
    return UserMapper.toDTO(userRepository.save(user));*/
    return null;
  }

  public List<UserDTO> findAllUsers() {
    return userRepository.findAll().stream()
        .map(user -> modelMapper.map(user, UserDTO.class))
        .toList();
  }

  public Optional<UserDTO> findUserById(Long id) {
    return userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class));
  }

  @Override
  public Optional<UserDTO> findUserByUsername(String username) {
    var user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(modelMapper.map(user, UserDTO.class));
  }

  public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
    var userOpt = userRepository.findById(id);
    if (userOpt.isPresent()) {
      var user = userOpt.get();
      user.setUsername(userDTO.username());
      user.setEmail(userDTO.email());
      return Optional.of(modelMapper.map(user, UserDTO.class));
    }
    return Optional.empty();
  }

  @Override
  public String updateUserToken(Long id) {
    var user = this.findUserById(id);
    if (user.isEmpty()) {
      return null;
    }
    return "TODO";
  }

  public Optional<UserDTO> deleteUser(Long id) {
    var userOpt = userRepository.findById(id);
    if (userOpt.isEmpty()) {
      return Optional.empty();
    }
    userRepository.deleteById(id);
    return Optional.of(modelMapper.map(userOpt, UserDTO.class));
  }
}
