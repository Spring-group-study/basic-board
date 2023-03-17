package com.study.board.service;

import com.study.board.entity.Member;

public interface LoginService {
    Member getMember(String checkId, String checkPw);
}
