package com.furkanbilgin.week3.springmvc.component;

import com.furkanbilgin.week3.springmvc.model.User;
import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getAge());
    }

    public User toUser(UserDTO userDTO) {
        var user =
                new User(
                        userDTO.getName(),
                        userDTO.getSurname(),
                        userDTO.getEmail(),
                        userDTO.getAge());
        user.setId(userDTO.getId());
        return user;
    }
}
