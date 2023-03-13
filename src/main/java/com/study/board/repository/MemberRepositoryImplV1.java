package com.study.board.repository;

import com.study.board.dto.MemberDto;
import com.study.board.entity.Member;
import com.study.board.entity.Post;
import com.study.board.mapper.MapperV4;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class MemberRepositoryImplV1 implements MemberRepositoryV1 {

    private final JdbcTemplate template;
    private final MapperV4 mapper;

    public MemberRepositoryImplV1(JdbcTemplate template, MapperV4 mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Override
    public Long save(MemberDto dto) {
        Member member = mapper.SaveDtoToEntity(dto);
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("member_id");

        Map<String, String> data = new HashMap<>();
        data.put("login_id", member.getLoginId());
        data.put("password", member.getPassword());
        data.put("nickname", member.getNickname());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(data));
        member.setMemberId(key.longValue());

        return member.getMemberId();
    }

    @Override
    public Member findById(Long id) {
        String sql = "select * from member where member_id=?";
        return template.queryForObject(sql, memberRowMapper(), id);
    }

    @Override
    public Member findByLoginId(String loginId) {
        String sql = "select * from member where login_id=?";
        return template.queryForObject(sql, memberRowMapper(), loginId);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs,rowNum)->{
            Member member = new Member(rs.getString("login_id"),
                    rs.getString("password"),
                    rs.getString("nickname"));
            member.setMemberId(rs.getLong("member_id"));
            return member;
        };
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        return template.query(sql, memberRowMapper());
    }

    @Override
    public Member update(Member member, MemberDto updateParam) {
        Member updatedMember = mapper.updateDtoToEntity(member, updateParam);
        String sql = "update member set(login_id,password,nickname)=(?,?,?) where member_id=?";
        template.update(sql, updatedMember.getLoginId(), updatedMember.getPassword(), updatedMember.getNickname(), updatedMember.getMemberId());
        return updatedMember;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from member where member_id=?";
        template.update(sql, id);
    }

    @Override
    public void clear() {
        String sql = "delete from member";
        template.execute(sql);
    }
}
