package com.study.board.repository;

import com.study.board.dto.MemberDto;
import com.study.board.dto.MemberDtoV2;
import com.study.board.entity.Member;
import com.study.board.entity.MemberV2;
import com.study.board.entity.PostV2;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryV2 {
    public Long save(MemberDtoV2 dto);

    public MemberV2 findById(Long id);

    public List<MemberV2> findByLoginId(String loginId);

    public List<MemberV2> findAll();

    public MemberV2 update(MemberV2 member, MemberDtoV2 updateParam);

    public void delete(Long id);

    public void clear();
}
