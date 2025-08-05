package com.furkanbilgin.week3.springmvc.controller;

import com.furkanbilgin.week3.springmvc.model.UserRegisterForm;
import com.furkanbilgin.week3.springmvc.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/register")
    public String getRegister(Model model) {
        UserRegisterForm form = new UserRegisterForm();
        model.addAttribute("form", form);
        return "register";
    }

    @PostMapping(value="/register")
    public String postRegister(@ModelAttribute("form") @Valid UserRegisterForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userRepository.saveUser(form);
        model.addAttribute("users", userRepository.getAllUsers());
        return "register-success";
    }
}
