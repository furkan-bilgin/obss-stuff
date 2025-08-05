package com.furkanbilgin.week3.springmvc.repository;

import com.furkanbilgin.week3.springmvc.model.UserRegisterForm;

import java.util.List;

public interface UserRepository {
    void saveUser(UserRegisterForm userRegisterForm);
    List<UserRegisterForm> getAllUsers();
}
