package com.study.board.controller;

import com.study.board.dto.LoginDto;
import com.study.board.entity.Member;
import com.study.board.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    public static final String MEMBER_ID = "memberId";
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto dto, @RequestParam(defaultValue = "/home") String redirectURL,
                        BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/loginForm";
        }
        Member member = loginService.getMember(dto.getLoginId(), dto.getLoginPw());
        if (member == null) {
            result.reject("loginFail","아아디 또는 비밀번호가 맞지않습니다.");
            return "/loginForm";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute(MEMBER_ID, member);
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        return "/loginForm";
    }
}
