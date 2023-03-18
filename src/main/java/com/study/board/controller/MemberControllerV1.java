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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
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
    public String login(@ModelAttribute("member") MemberDto member) {
        return "member/login";
    }

    @PostMapping("/login")  //문제있음..로그인 버튼 누르면 아무일도 안일어남
    public String login(@Validated @ModelAttribute("member") MemberDto member, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "member/login";
        }
        Member loginMember = memberServiceV1.login(member.getLoginId(), member.getPassword());
        if (loginMember == null) {
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호 오류입니다.");
            System.out.println("========로그인 실패========");
            return "member/login";
        }

        //로그인 성공시
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        System.out.println("========로그인 성공 : "+loginMember.getLoginId()+"||"+loginMember.getNickname()+"========");
        return "redirect:"+redirectURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("member", new MemberDto());
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") MemberDto member, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            System.out.println("========회원가입 오류========");
            return "member/register";
        }
        Long memberId = memberServiceV1.save(member);
        Member registerMember = memberServiceV1.findById(memberId);
        System.out.println("========회원가입 성공========");

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, registerMember);
        System.out.println("========로그인 성공 : "+registerMember.getLoginId()+"||"+registerMember.getNickname()+"========");

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> System.out.println("session-name = "+name+"   value = "+ session.getAttribute(name)));

        System.out.println("ID= "+memberServiceV1.findById(memberId).getLoginId());
        redirectAttributes.addAttribute("memberId", memberId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}")
    public String individualPage(@PathVariable Long memberId, Model model) {
        Member member = memberServiceV1.findById(memberId);
        model.addAttribute("member", member);
        return "member/individual";
    }
}
