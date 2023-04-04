package com.study.board.mapper;

import com.study.board.dto.MemberDto;
import com.study.board.dto.MemberDtoV2;
import com.study.board.dto.PostDto;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.Member;
import com.study.board.entity.MemberV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.login.session.SessionConst;
import com.study.board.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 멤버 dto -> 객체 메서드 추가
 */

@Component
public class MapperV5 {

    public PostV2 postSaveDtoToEntity(PostDtoV2 dto, HttpServletRequest request) {
        PostV2 post = new PostV2(getMemberFromSession(request), dto.getTitle(), dto.getContent());
        return post;
    }

    public PostV2 postUpdateDtoToEntity(PostV2 post, PostDtoV2 dto) {
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return post;
    }

    public PostDtoV2 postEntityToDto(PostV2 post) {
        PostDtoV2 dto = new PostDtoV2();
        dto.setNickname(post.getMember().getNickname());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        return dto;
    }

    public MemberV2 getMemberFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MemberV2 authorMember = (MemberV2) session.getAttribute(SessionConst.LOGIN_MEMBER);
        return authorMember;
    }

    public MemberV2 memberSaveDtoToEntity(MemberDtoV2 dto) {
        MemberV2 member = new MemberV2(dto.getLoginId(), dto.getPassword(), dto.getNickname());
        return member;
    }

    public MemberV2 memberUpdateDtoToEntity(MemberV2 member, MemberDtoV2 dto) {
        member.setLoginId(dto.getLoginId());
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());

        return member;
    }
}
