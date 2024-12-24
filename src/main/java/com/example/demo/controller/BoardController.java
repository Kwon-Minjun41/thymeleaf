package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BoardController {
    @GetMapping("/board/write")
    public String write() {
        return "write";
    }
}
