package com.study.board.mapper;

import com.study.board.dto.MemberDto;
import com.study.board.dto.PostDto;
import com.study.board.entity.Member;
import com.study.board.entity.Post;

/**
 * 멤버 dto -> 객체 메서드 추가
 */

public class MapperV4 {
    public Post SaveDtoToEntity(PostDto dto) {
        //롬복 추가 후 builder패턴 이용할것
        Post post = new Post(dto.getAuthor(), dto.getTitle(), dto.getContent());
        return post;
    }

    public Post updateDtoToEntity(Post post, PostDto dto) {
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }

    public Member SaveDtoToEntity(MemberDto dto) {
        Member member = new Member(dto.getLoginId(), dto.getPassword(), dto.getNickname());
        return member;
    }

    public Member updateDtoToEntity(Member member, MemberDto dto) {
        member.setLoginId(dto.getLoginId());
        member.setPassword(dto.getPassword());
        member.setNickname(dto.getNickname());

        return member;
    }
}
