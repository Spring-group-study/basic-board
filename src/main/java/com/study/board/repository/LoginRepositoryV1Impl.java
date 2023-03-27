package com.study.board.repository;

import com.study.board.entity.MyMember;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginRepositoryV1Impl implements LoginRepositoryV1 {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepositoryV1Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MyMember findMember(String checkId, String checkPw) {
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND PASSWORD=?";
        List<MyMember> myMembers = jdbcTemplate.query(sql, new MemberMapper(), checkId, checkPw);
        if (myMembers.isEmpty()) {
            return null;
        }else {
            return myMembers.get(0);
        }

    }

    static class MemberMapper implements RowMapper<MyMember> {
        @Override
        public MyMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            MyMember myMember = new MyMember(
                    rs.getLong("id"), rs.getString("member_id"),
                    rs.getString("password"));
            return myMember;
        }
    }
}
