package com.example.demo.controller;


import com.example.demo.dto.DataDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {
    @GetMapping
    public String index() {//@RequestParam("id") String id) {
        //System.out.println("id: "+id);
        log.info("log test:{}", "ok");
        return "index"; //index.html
    }

    @GetMapping("/intro")
    public String intro(Model model) {
        model.addAttribute("currentDate", LocalDateTime.now());
        return "intro";
    }
    @GetMapping("/t_output")
    public String output(Model model) {
        log.info("============================t_output ok===============================");
        model.addAttribute("htmlStr", "<h3>h3만든 문자열</h3>");
        //map 데이터 묶음
        Map<String, String> map = new HashMap<>();
        map.put("name","홍길동");
        map.put("age","20");
        map.put("addr","인천 미추홀구");
        model.addAttribute("mapData", map);
        //Dto(또는 vo) 데이터 묶음
        DataDto dto = new DataDto(); // 자동완성: alt + enter
        dto.setName("아무개");
        dto.setAge(20);
        dto.setAddress("인천시 서구");
        model.addAttribute("dtoData", dto);
        model.addAttribute("msg", "서버로부터의 메세지");
        return "t_output";
    }
//   @RequestMapping(value = "/t_control",method = RequestMethod.GET)
    @GetMapping("/t_control")
    public String control(Model model,HttpSession session ){//HttpServletRequest req) {
//        HttpSession session = req.getSession();
        session.setAttribute("user_id", "admin"); //로그인 성공
        model.addAttribute("ss", session);
//        session.invalidate(); //로그아웃
        model.addAttribute("msg", "이 문자열이 보입니다");
        model.addAttribute("age", 34);

        List<DataDto> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DataDto dto = new DataDto();
            dto.setName("이름"+i);
            dto.setAge(20+i);
            dto.setAddress("주소"+i);
            list.add(dto);
        }
        model.addAttribute("list", list);
        return "t_control";
   }
   @GetMapping("/info/{id}")
    public String info(Model model,@PathVariable("id") String id) {
       System.out.println("id: "+id);
       return null;
   }
   @GetMapping("/sendData")
    public String sendData() {
        return "sendData";
   }
   @GetMapping("/a_send")
   public String aSend(Model model,@RequestParam("num1")String num1, @RequestParam("num2")String num2) {
       System.out.println("num1: "+num1);
       System.out.println("num2: "+num2);
       model.addAttribute("result", num1+num2);
       return "s_result";
   }
   @GetMapping("/noneDtoSend")
    public String noneDtoSend(@RequestParam String name,
                              @RequestParam(value = "age",defaultValue = "1")int age,
                              @RequestParam String address,
                              Model model) {
       System.out.println("name: "+name);
       System.out.println("age: "+age);
       System.out.println("address: "+address);
       model.addAttribute("result", "none dto send ok");
        return "s_result";
   }
  @PostMapping("/dtoSend")
    public String dtoSend(@ModelAttribute DataDto dto, Model model) {
      System.out.println("name: "+dto.getName());
      System.out.println("age: "+dto.getAge());
      System.out.println("address: "+dto.getAddress());
      model.addAttribute("result", "dto send ok");
      return "s_result";
  }
  @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", new DataDto("cha",20,"안산"));
        return "user";
    }
    @PostMapping("/user")
    public String user(@ModelAttribute DataDto dto, Model model) {
        System.out.println("name: "+dto.getName());
        System.out.println("age: "+dto.getAge());
        System.out.println("address: "+dto.getAddress());
        return "user";
    }
}

