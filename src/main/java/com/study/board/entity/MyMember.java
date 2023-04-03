package com.study.board.entity;

public class MyMember {

    private Long id;
    private String memberId;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyMember(Long id, String memberId, String password) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
    }
}
