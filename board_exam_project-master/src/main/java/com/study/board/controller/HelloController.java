package com.study.board.controller;

import com.study.board.domain.ResultVO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello") // 어떤 메소드든 받는다.
    public String hello() {
        return "Hello test";
    }

    @GetMapping("/hello2") // GET 메소드만 받는다.
    public String hello2() {
        return "Hello GET test";
    }

    @GetMapping("/hello3")
    // URL에 query parameter를 요구한다.
    // localhost:8080/hello3?name=gildong
    public String hello3(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello32/{name}")
    // URL에 URI parameter를 요구한다.
    // localhost:8080/hello32/gildong
    public String hello32(@PathVariable String name) {
        return "Hello" + name;
    }

    // POST 메소드에서도 Query Parameter를 사용할 수 있다.
    @PostMapping("/hello33")
    // localhost:8080/hello3?name=gildong
    public String hello33(@RequestParam("name") String name) {
        return "Hello" + name;
    }

    // POST 메소드
    @PostMapping("/hello4")
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }

    // Lombok을 사용한 JSON 데이터 리턴
    @PostMapping("/hello5")
    public ResultVO hello5(@RequestParam("name") String name) {
        ResultVO result = new ResultVO(0, name);
        // Spring Framework의 Jackson Mapper가 Java 객체를 알아서 JSON으로 변환해 준다.
        return result;
    }

    // JSON으로 데아터 보내기
    @PostMapping("/hello6")
    public ResultVO hello6(@RequestBody ResultVO result) {
        return result;
    }

}