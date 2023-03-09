package com.study.board.service;

import com.study.board.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
   private final LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public String getMember(String checkId, String checkPw) {
        return loginRepository.findMember(checkId,checkPw);
    }


}
