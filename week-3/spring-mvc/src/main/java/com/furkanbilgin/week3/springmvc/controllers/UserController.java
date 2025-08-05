package com.furkanbilgin.week3.springmvc.controllers;

import com.furkanbilgin.week3.springmvc.models.UserRegisterForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final List<UserRegisterForm> userRegisterForms = new ArrayList<>();

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
        userRegisterForms.add(form);
        model.addAttribute("users", userRegisterForms);
        return "register-success";
    }
}
