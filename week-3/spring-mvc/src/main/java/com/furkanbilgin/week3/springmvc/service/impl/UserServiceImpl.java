package com.furkanbilgin.week3.springmvc.service.impl;

import com.furkanbilgin.week3.springmvc.component.PasswordHasher;
import com.furkanbilgin.week3.springmvc.component.UserMapper;
import com.furkanbilgin.week3.springmvc.exception.UserNotFoundException;
import com.furkanbilgin.week3.springmvc.model.SystemRole;
import com.furkanbilgin.week3.springmvc.model.User;
import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;
import com.furkanbilgin.week3.springmvc.repository.UserRepository;
import com.furkanbilgin.week3.springmvc.service.RoleService;
import com.furkanbilgin.week3.springmvc.service.UserService;

import jakarta.transaction.Transactional;

import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final List<Pair<User, SystemRole>> defaultUsers =
            List.of(
                    new Pair<>(
                            User.builder().username("admin").password("123456").build(),
                            SystemRole.ADMIN),
                    new Pair<>(
                            User.builder().username("user").password("merhaba").build(),
                            SystemRole.USER));
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordHasher passwordHasher;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            RoleService roleService,
            PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        var user = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // Check if the user exists
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        // Update the user
        userDTO.setId(id);
        var user = userMapper.toUser(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }

    @Override
    public UserDTO findUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO findUserByUsernameAndPassword(String username, String password) {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        if (!passwordHasher.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Invalid password for user: " + username);
        }
        return userMapper.toUserDTO(user);
    }

    @Override
    @Transactional
    public void checkAndAddUsers() {
        for (var pair : defaultUsers) {
            var user = pair.a;
            var systemRole = pair.b;
            if (userRepository.findByUsername(user.getUsername()) != null) {
                continue; // Skip if exists
            }
            user.getRoles().add(roleService.findRoleByName(systemRole.name()));
            user.setPassword(passwordHasher.hashPassword(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
    }
}
