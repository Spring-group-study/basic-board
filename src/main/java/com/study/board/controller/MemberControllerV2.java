package com.study.board.controller;

import com.study.board.dto.LoginDto;
import com.study.board.dto.LoginDtoV2;
import com.study.board.dto.MemberDto;
import com.study.board.dto.MemberDtoV2;
import com.study.board.entity.Member;
import com.study.board.entity.MemberV2;
import com.study.board.entity.PostV2;
import com.study.board.login.session.SessionConst;
import com.study.board.service.MemberServiceV1;
import com.study.board.service.MemberServiceV2;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberControllerV2 {

    private final MemberServiceV2 memberServiceV2;

    @GetMapping("/login")
    public String login(Model model) {
        LoginDtoV2 member = new LoginDtoV2();
        model.addAttribute("member", member);
        return "member/login";
    }

    @PostMapping("/login")  //로그인DTO를 따로 만들어 줘야 오류가 안남
    public String login(@Validated @ModelAttribute("member") LoginDtoV2 member, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURI, HttpServletRequest request) {

        List<MemberV2> idChecked = memberServiceV2.findByLoginId(member.getLoginId());

        if (idChecked.isEmpty()) {
            bindingResult.rejectValue("loginId","loginIdFailure");
            return "member/login";
        }
        //ID 검증 완료

        if (!idChecked.get(0).getPassword().equals(member.getPassword())) {
            bindingResult.rejectValue("password","loginPwFailure");
        }
        //PW 검증 완료

        if (bindingResult.hasErrors()) {
            return "member/login";
        }

        MemberV2 loginMember = idChecked.get(0);
        //여기까지 내려오면 로그인 검증 완료

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURI;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        MemberDtoV2 memberDtoV2 = new MemberDtoV2();
        model.addAttribute("member", memberDtoV2);
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("member") MemberDtoV2 member, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {

        if (!memberServiceV2.findByLoginId(member.getLoginId()).isEmpty()) {
            bindingResult.rejectValue("loginId","registerFailure");
        }

        if (bindingResult.hasErrors()) {
            //얘가 마지막에 와야함
            return "member/register";
        }


        Long savedMemberId = memberServiceV2.save(member);
        MemberV2 savedMember = memberServiceV2.findById(savedMemberId);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, savedMember);

        redirectAttributes.addAttribute("memberId", savedMemberId);
        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}")
    public String individualPage(@PathVariable Long memberId, Model model) {
        MemberV2 member = memberServiceV2.findById(memberId);
        model.addAttribute("member", member);
        return "member/individual";
    }

    @GetMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId,Model model) {
        MemberV2 member = memberServiceV2.findById(memberId);
        MemberDtoV2 dto = new MemberDtoV2(member.getLoginId(), member.getNickname(), member.getPassword());
        model.addAttribute("member", dto);
        model.addAttribute("memberId", memberId);
        return "member/edit";
    }

    @PostMapping("/individual/{memberId}/edit")
    public String edit(@PathVariable Long memberId, @Validated @ModelAttribute("member") MemberDtoV2 dto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "member/edit";
        }
        MemberV2 member = memberServiceV2.findById(memberId);
        memberServiceV2.update(member, dto);
        redirectAttributes.addAttribute("memberId", member.getMemberId());
        return "redirect:/member/individual/{memberId}";
    }

    @GetMapping("/individual/{memberId}/delete")
    public String delete(@PathVariable Long memberId,HttpServletRequest request) {
        memberServiceV2.delete(memberId);
        request.getSession().invalidate();
        return "member/delete";
    }
}
