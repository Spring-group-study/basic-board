package com.study.board.controller;

import com.study.board.dto.LoginDto;
import com.study.board.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("member", new LoginDto());
        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/login";
        }
        String answer = loginService.getMember(dto.getLoginId(), dto.getLoginPw());
        if (answer == null) {
            return "redirect:/login";
        }else {
            return "/home";
        }
    }
}
