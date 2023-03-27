package com.study.board.service;

import com.study.board.entity.Member;
import com.study.board.repository.LoginRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceV2 {
    private final LoginRepositoryV2 repositoryV2;

    public Member findMember(String checkId, String checkPw) {
        return repositoryV2.findByMemberIdAndPassword(checkId,checkPw);
    }

}
