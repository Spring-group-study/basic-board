package com.study.board.controller;

import com.study.board.dto.LoginDto;
import com.study.board.dto.MemberDto;
import com.study.board.entity.Member;
import com.study.board.login.session.SessionConst;
import com.study.board.service.MemberServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControllerV2 {

    private final MemberServiceV1 memberServiceV2;

    @GetMapping("/login")
    public String login(Model model) {

        return "member/login";
    }

    @PostMapping("/login")  //로그인DTO를 따로 만들어 줘야 오류가 안남
    /**
     * 그냥 멤버DTO 로 하면 nickname이 null이라서 오류가 남 -> MemberDto의 nickname에 @NotBlank를 주석처리하면 되긴 함
     * 로그인 DTO를 따로 만들기로 결정함
     */
    public String login(@Validated @ModelAttribute("member") LoginDto member, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI, HttpServletRequest request) {

        return "redirect:" + redirectURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {

        return "member/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") MemberDto member, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {

        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}")
    public String individualPage(@PathVariable Long memberId, Model model) {

        return "member/individual";
    }
}
