package com.study.board.service;

import com.study.board.dto.MemberDto;
import com.study.board.entity.Member;
import com.study.board.repository.MemberRepositoryV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceV1 {

    private final MemberRepositoryV1 memberRepositoryV1;

    public MemberServiceV1(MemberRepositoryV1 memberRepositoryV1) {
        this.memberRepositoryV1 = memberRepositoryV1;
    }

    public Long save(MemberDto dto) {
        return memberRepositoryV1.save(dto);
    }

    public Member login(String loginId, String pw) {
        Member member = memberRepositoryV1.findByLoginId(loginId);
        if (member.getLoginId().equals(loginId)&&member.getPassword().equals(pw)) {
                return member;
        }else{
                return null;
            }
    }

    public Member findById(Long id) {
        return memberRepositoryV1.findById(id);
    }

    public List<Member> findAll() {
        return memberRepositoryV1.findAll();
    }

    public Member update(Member member,MemberDto dto) {
        return memberRepositoryV1.update(member,dto);
    }

    public void delete(Long id) {
        memberRepositoryV1.delete(id);
    }

}
