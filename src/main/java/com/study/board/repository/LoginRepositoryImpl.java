package com.study.board.repository;

import com.study.board.entity.Member;
import com.study.board.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

    private final JdbcTemplate jdbcTemplate;

    public LoginRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Member findMember(String checkId, String checkPw) {
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND PASSWORD=?";
        List<Member> members = jdbcTemplate.query(sql, new MemberMapper(), checkId, checkPw);
        return members.get(0);

    }

    static class MemberMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getLong("id"), rs.getString("member_id"),
                    rs.getString("password"));
            return member;
        }
    }
}
