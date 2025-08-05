package com.furkanbilgin.week3.springmvc.repositories.impl;

import com.furkanbilgin.week3.springmvc.models.UserRegisterForm;
import com.furkanbilgin.week3.springmvc.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final List<UserRegisterForm> userRegisterForms = new ArrayList<>();

    @Override
    public void saveUser(UserRegisterForm userRegisterForm) {
        userRegisterForms.add(userRegisterForm);
    }

    @Override
    public List<UserRegisterForm> getAllUsers() {
        // Return a *copy*
        return new ArrayList<>(userRegisterForms);
    }
}
