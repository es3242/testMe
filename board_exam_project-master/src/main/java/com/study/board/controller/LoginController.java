package com.study.board.controller;

import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "klogin02";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("id") Long id,
                                   @RequestParam("password") String password,
                                   HttpSession session, Model model) {

        System.out.println(id);
        System.out.println(password);
        // Authenticate user based on id and password
        boolean isAuthenticated = userService.authenticate(id, password);
        System.out.println(isAuthenticated);
        if (isAuthenticated) {
            session.setAttribute("user", id);
            return "redirect:/";
        } else {
            model.addAttribute("error", "실패!");
            return "Klogin02";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

