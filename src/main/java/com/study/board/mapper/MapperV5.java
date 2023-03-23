package com.study.board.mapper;

import com.study.board.dto.MemberDto;
import com.study.board.dto.MemberDtoV2;
import com.study.board.dto.PostDto;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.Member;
import com.study.board.entity.MemberV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import org.springframework.stereotype.Component;

/**
 * 멤버 dto -> 객체 메서드 추가
 */

@Component
public class MapperV5 {

    public PostV2 SaveDtoToEntity(PostDtoV2 dto) {
        PostV2 post = new PostV2(dto.getAuthor(), dto.getTitle(), dto.getContent());
        return post;
    }

    public PostV2 updateDtoToEntity(PostV2 post, PostDtoV2 dto) {
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }

    public MemberV2 SaveDtoToEntity(MemberDtoV2 dto) {
        MemberV2 member = new MemberV2(dto.getLoginId(), dto.getPassword(), dto.getNickname());
        return member;
    }

    public MemberV2 updateDtoToEntity(MemberV2 member, MemberDtoV2 dto) {
        member.setLoginId(dto.getLoginId());
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());

        return member;
    }
}
