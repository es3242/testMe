package com.study.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping("/api")
public class RestController {

    @GetMapping("/data")
    public String getData() {
        return "Hello from Spring!";
    }
}
