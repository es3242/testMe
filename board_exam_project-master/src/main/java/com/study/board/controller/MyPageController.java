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
import java.util.List;

@RestController
public class MyPageController {
    @Autowired
    private UserService userService;
    @Autowired
    private FreeboardService freeboardService;

    @GetMapping("/mypage")
    @ResponseBody
    public ResponseEntity<?> mypage(HttpSession session) {
        Object obj = session.getAttribute("user"); // 사용자 정보 받아서 오브젝트로 만들기
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자가 로그인되어 있지 않습니다.");
        } else {
            // 사용자 id를 Long 타입으로 변환
            Long userId = Long.parseLong(obj.toString());
            // 사용자 정보 불러오기
            User user = userService.getUserInfo(userId);

            // freeboard에서 사용자가 작성한 글 불러오기
            List<Freeboard> userFreeboards = freeboardService.getContentByUserId(userId);

            return ResponseEntity.ok().body(userFreeboards);
        }
    }
}
