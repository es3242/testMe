package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String main() {
        return "main";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        userService.register(user);
        model.addAttribute("message", "회원가입 환영합니다!");
        return "main";
    }


}