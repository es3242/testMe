package com.study.board.controller;

import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;

    @GetMapping("/mypage/{nickname}")
    public String mypage(@PathVariable("nickname") String nickname, Model model) {
        model.addAttribute("user", userService.getUserContents(nickname));
        return "users/mypage";
    }
}
