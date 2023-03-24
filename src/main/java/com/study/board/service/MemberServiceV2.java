package com.study.board.service;

import com.study.board.dto.MemberDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final MemberRepositoryV2 memberRepository;

    public Long save(MemberDtoV2 dto) {
        return memberRepository.save(dto);
    }

    public MemberV2 login(String loginId, String pw) {
        MemberV2 member = memberRepository.findByLoginId(loginId);
        if (member != null && member.getPassword().equals(pw)) {
            return member;
        }else{
            return null;
        }
    }

    public MemberV2 findById(Long id) {
        return memberRepository.findById(id);
    }

    public List<MemberV2> findAll() {
        return memberRepository.findAll();
    }

    public MemberV2 update(MemberV2 member, MemberDtoV2 dto) {
        return memberRepository.update(member, dto);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }
}
