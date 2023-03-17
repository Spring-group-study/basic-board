package com.study.board.controller;

import com.study.board.entity.Member;
import com.study.board.resolver.Login;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(@Login Member loginMember) {
        if (loginMember == null) {
            return "loginForm";
        }
        return "home";
    }

    @GetMapping("/home")
    public String goToHome() {
        return "home";
    }
}
