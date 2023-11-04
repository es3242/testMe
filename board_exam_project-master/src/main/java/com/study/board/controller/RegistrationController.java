package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return "index02";
    }
    @GetMapping("/aa")
    public String mainaaa() {
        return "meetings02";
    }
    @GetMapping("/bb")
    public String mainabb() {
        return "meeting-details02";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "kregister";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@ModelAttribute("user") User user) {

        userService.register(user);
        System.out.println("Registered!");
        System.out.println(user.getNickname());

        // return "index02";
        return new ResponseEntity<>("회원가입이 완료되었습니다!", HttpStatus.OK);
    }
}