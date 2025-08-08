package com.furkanbilgin.finalproject.movieportal.service.impl;

import com.furkanbilgin.finalproject.movieportal.dto.CourseDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.RegisterUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.user.UserDTO;
import com.furkanbilgin.finalproject.movieportal.mapper.CourseMapper;
import com.furkanbilgin.finalproject.movieportal.mapper.UserMapper;
import com.furkanbilgin.finalproject.movieportal.model.User;
import com.furkanbilgin.finalproject.movieportal.repository.CourseRepository;
import com.furkanbilgin.finalproject.movieportal.repository.UserRepository;
import com.furkanbilgin.finalproject.movieportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public UserDTO saveUser(RegisterUserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        return UserMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDTO).orElse(null);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            // Update roles and courses as needed
            return UserMapper.toDTO(userRepository.save(user));
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<CourseDTO> getMyCourses(String username) {
        return userRepository.findByUsername(username).get().getCourses().stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
