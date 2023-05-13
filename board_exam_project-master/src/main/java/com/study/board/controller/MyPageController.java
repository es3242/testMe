package com.study.board.controller;

import com.study.board.entity.Freeboard;
import com.study.board.entity.User;
import com.study.board.service.FreeboardService;
import com.study.board.service.UserService;
import org.python.antlr.op.In;
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
            return "redirect:/login";// user가 없는 경우 예외 처리
        } else {
            //사용자 id를 Long 타입으로 변환
            Long userId = Long.parseLong(obj.toString());
            //사용자 정보 불러오기
            model.addAttribute("user", userService.getUserInfo(userId));

            //freeboard에서 사용자가 작성한 글 불러오기
            /*
            * 1. 사용자 id를 기준으로 contentId를 찾기. getFreeboardById는 contentId 기준 함수
            * 2. contentId를 사용해서 각 게시글을 찾기
            * 3. 찾은 게시글들을 List 형식으로 묶든 해서 html에 보낼 수 있게 하기
            * */

            List<Freeboard> contentIds = freeboardService.getContentByUserId(userId); //getContentByUserId는 userId로 작성된 contentId를 Freeboard 타입 List로 반환한다.
            model.addAttribute("userFreeboard", contentIds); //반환받은 List를 userFreeboard라는 이름으로 전달한다.

        }

        return "users/mypage";
    }
}




