package com.study.board.repository;

import com.study.board.entity.Member;

public interface LoginRepository {
    Member findMember(String checkId, String checkPw);
}
