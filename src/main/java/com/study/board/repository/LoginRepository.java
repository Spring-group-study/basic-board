package com.study.board.repository;

public interface LoginRepository {
    String findMember(String checkId, String checkPw);
}
