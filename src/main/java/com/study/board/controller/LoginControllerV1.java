package com.study.board.controller;

import com.study.board.dto.LoginDto;
import com.study.board.entity.MyMember;
import com.study.board.service.LoginServiceV1;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Controller
public class LoginControllerV1 {

    public static final String MEMBER_ID = "memberId";
    private final LoginServiceV1 loginServiceV1;

    public LoginControllerV1(LoginServiceV1 loginServiceV1) {
        this.loginServiceV1 = loginServiceV1;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto) {
        return "/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginDto dto, BindingResult result,
                        HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/loginForm";
        }
        MyMember myMember = loginServiceV1.getMember(dto.getLoginId(), dto.getLoginPw());
        if (myMember == null) {
            result.reject("loginFail","아아디 또는 비밀번호가 맞지않습니다.");
            return "/loginForm";
        }else {
            HttpSession session = request.getSession();
            String redirector = request.getParameter("redirector");
            if (redirector == null){
                redirector = "/";
            }
            session.setAttribute(MEMBER_ID, myMember);
            return "redirect:"+redirector;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/loginForm";
    }
}
