package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private FreeboardService freeboardService;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Object obj = session.getAttribute("user"); //사용자 정보 받아서 오브젝트로 만들기
        if (obj == null) {
            // user가 없는 경우 예외 처리
        } else {
            Long userId = Long.parseLong(obj.toString());
            model.addAttribute("user", userService.getUserInfo(userId));
        }
        return "users/mypage";
    }
}




