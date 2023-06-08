package com.study.board.controller;

import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestParam("id") Long id,
                                   @RequestParam("password") String password,
                                   HttpSession session) {

        System.out.println(id);
        System.out.println(password);
        // Authenticate user based on id and password
        boolean isAuthenticated = userService.authenticate(id, password);
        System.out.println(isAuthenticated);
        if (isAuthenticated) {
            session.setAttribute("user", id);
            return new ResponseEntity<>("Logged in", HttpStatus.OK);
            // return "redirect:/";
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    @GetMapping("/session-info")
    public ResponseEntity<String> sessionInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return new ResponseEntity<>("null", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(userId.toString(), HttpStatus.OK);
        }

    }


    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        System.out.println("Logged out");
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }


}

