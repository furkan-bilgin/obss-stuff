package com.furkanbilgin.week3.springmvc.repositories;

import com.furkanbilgin.week3.springmvc.models.UserRegisterForm;

import java.util.List;

public interface UserRepository {
    void saveUser(UserRegisterForm userRegisterForm);
    List<UserRegisterForm> getAllUsers();
}
