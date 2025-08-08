package com.furkanbilgin.finalproject.movieportal.mapper;

import com.furkanbilgin.finalproject.movieportal.dto.RegisterUserDTO;
import com.furkanbilgin.finalproject.movieportal.dto.UserDTO;
import com.furkanbilgin.finalproject.movieportal.model.User;
import com.furkanbilgin.finalproject.movieportal.model.Role;
import com.furkanbilgin.finalproject.movieportal.model.Course;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles() != null ? user.getRoles().stream().map(Role::getName).collect(Collectors.toList()) : null)
                .courseIds(user.getCourses() != null ? user.getCourses().stream().map(Course::getId).collect(Collectors.toList()) : null)
                .build();
    }

    public static User toEntity(UserDTO dto, List<Role> roles, List<Course> courses) {
        if (dto == null) return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .roles(roles)
                .courses(courses)
                .build();
    }

    public static User toEntity(RegisterUserDTO dto) {
        if (dto == null) return null;
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}

