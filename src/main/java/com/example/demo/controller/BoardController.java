package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping(value = "/board") //get, post 둘다 받음
public class BoardController {
    //localhost/board
    @GetMapping({"/",""}) // /board 생략
    @ResponseBody
    public String list() {
        System.out.println("list 목록보기");
        return "/board/list 요청";
    }

//    @GetMapping("/board/read/{no}")
//    public String read(@PathVariable("no") String no) {
//        System.out.println("글1개 읽기");
//        return "null";
//    }

    @GetMapping("/write")
    @ResponseBody
    public String write() {
        System.out.println("글쓰기 ");
        return "/board/write 요청";
    }
}
