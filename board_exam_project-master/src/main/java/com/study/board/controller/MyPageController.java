package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private FreeboardService freeboardService;

    @GetMapping("/mypage")
    @ResponseBody
    public ResponseEntity<?> mypage(HttpSession session) {
        Object obj = session.getAttribute("user");
        System.out.println(obj);
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not logged in.");
        } else {
            Long userId = Long.parseLong(obj.toString());
            System.out.println(userId);

            User user = userService.getUserInfo(userId);
            System.out.println(user);

            List<Freeboard> userFreeboards = freeboardService.getContentByUserId(userId);
            System.out.println(userFreeboards);

            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("userFreeboards", userFreeboards);

            return ResponseEntity.ok().body(response);
        }
    }
}

