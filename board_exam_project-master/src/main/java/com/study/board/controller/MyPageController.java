package com.study.board.controller;

import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Object obj = session.getAttribute("user");
        if (obj == null) {
            // user가 없는 경우 예외 처리
        } else {
            Long userId = Long.parseLong(obj.toString());
            model.addAttribute("user", userService.getUserContents(userId));
        }
        return "users/mypage";
    }
}




