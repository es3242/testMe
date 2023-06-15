package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private FreeboardService freeboardService;

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        Object obj = session.getAttribute("user");
        System.out.println(obj);
        if (obj == null) {
            return "user/mypage";
        } else {
            Long userId = Long.parseLong(obj.toString());
            System.out.println(userId);

            User user = userService.getUserInfo(userId);
            System.out.println(user);

            List<Freeboard> userFreeboards = freeboardService.getContentByUserId(userId);
            System.out.println(userFreeboards);

            model.addAttribute("user", user);
            model.addAttribute("userFreeboard", userFreeboards);
            return "kmypage";
                    //ResponseEntity.ok().body(response);
        }
    }

    @GetMapping("/mypage2")
    public ResponseEntity<Map<String, Object>> mypage2(HttpSession session) {
        Object obj = session.getAttribute("user");
        System.out.println(obj);

        if (obj == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Long userId = Long.parseLong(obj.toString());
            System.out.println(userId);

            User user = userService.getUserInfo(userId);
            System.out.println(user);

            List<Freeboard> userFreeboards = freeboardService.getContentByUserId(userId);
            System.out.println(userFreeboards);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("user", user);
            responseBody.put("userFreeboard", userFreeboards);

            return ResponseEntity.ok(responseBody);
        }
    }
}

