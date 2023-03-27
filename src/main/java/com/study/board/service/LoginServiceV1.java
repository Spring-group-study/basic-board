package com.study.board.service;

import com.study.board.entity.MyMember;

public interface LoginServiceV1 {
    MyMember getMember(String checkId, String checkPw);
}
