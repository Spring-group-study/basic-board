package com.study.board.repository;

import com.study.board.entity.MyMember;

public interface LoginRepositoryV1 {
    MyMember findMember(String checkId, String checkPw);
}
