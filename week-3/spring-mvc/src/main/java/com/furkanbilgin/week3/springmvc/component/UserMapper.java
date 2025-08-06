package com.furkanbilgin.week3.springmvc.component;

import com.furkanbilgin.week3.springmvc.model.User;
import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    public User toUser(UserDTO userDTO) {
        return User.builder().id(userDTO.getId()).username(userDTO.getUsername()).build();
    }
}
