package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
