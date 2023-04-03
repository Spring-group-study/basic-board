package com.study.board.service;

import com.study.board.entity.MyMember;
import com.study.board.repository.LoginRepositoryV1;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceV1Impl implements LoginServiceV1 {
   private final LoginRepositoryV1 loginRepositoryV1;

    public LoginServiceV1Impl(LoginRepositoryV1 loginRepositoryV1) {
        this.loginRepositoryV1 = loginRepositoryV1;
    }

    @Override
    public MyMember getMember(String checkId, String checkPw) {

        return loginRepositoryV1.findMember(checkId,checkPw);
    }


}
