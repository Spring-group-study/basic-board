package com.study.board.controller;

import com.study.board.dto.MemberDto;
import com.study.board.entity.Member;
import com.study.board.login.session.SessionConst;
import com.study.board.login.web.Login;
import com.study.board.service.MemberServiceV1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberControllerV1 {

    private final MemberServiceV1 memberServiceV1;

    public MemberControllerV1(MemberServiceV1 memberServiceV1) {
        this.memberServiceV1 = memberServiceV1;
    }

    @GetMapping("/login")
    public String login(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "/member/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute MemberDto dto, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "/member/login";
        }
        Member loginMember = memberServiceV1.login(dto.getMemberId(), dto.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호 오류입니다.");
            return "/member/login";
        }

        //로그인 성공시
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    //로그아웃 구현 필요


    @GetMapping("/register")
    public String register() {

    }

    @PostMapping("/register")
    public String register() {

    }
}
