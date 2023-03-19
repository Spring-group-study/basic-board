package com.study.board.repository;

import com.study.board.dto.MemberDto;
import com.study.board.dto.PostDto;
import com.study.board.entity.Member;
import com.study.board.entity.Post;
import com.study.board.paging.Pagination;

import java.util.List;

public interface MemberRepositoryV1 {
    public Long save(MemberDto dto);

    public Member findById(Long id);

    public Member findByLoginId(String loginId);

    public List<Member> findAll();

    public Member update(Member member, MemberDto updateParam);

    public void delete(Long id);

    public void clear();
}
