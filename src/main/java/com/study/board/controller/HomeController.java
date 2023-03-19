package com.study.board.controller;

import com.study.board.login.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }
        if (session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            return "index";
        }
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
        return "index_login";
    }
}
