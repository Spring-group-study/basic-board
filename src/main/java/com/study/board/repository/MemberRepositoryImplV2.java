package com.study.board.repository;

import com.study.board.dto.MemberDto;
import com.study.board.dto.MemberDtoV2;
import com.study.board.entity.Member;
import com.study.board.entity.MemberV2;
import com.study.board.mapper.MapperV4;
import com.study.board.mapper.MapperV5;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImplV2 implements MemberRepositoryV2 {

    private final EntityManager em;
    private final MapperV5 mapper;

    @Override
    public Long save(MemberDtoV2 dto) {
        MemberV2 member = mapper.SaveDtoToEntity(dto);
        em.persist(member);
        return member.getMemberId();
    }

    @Override
    public MemberV2 findById(Long id) {
        return em.find(MemberV2.class, id);
    }

    @Override
    public MemberV2 findByLoginId(String loginId) {
        return em.createQuery("select m from MemberV2 m where m.loginId=:loginIdParam", MemberV2.class)
                .setParameter("loginIdParam", loginId)
                .getSingleResult();
    }

    @Override
    public List<MemberV2> findAll() {
        List<MemberV2> list = em.createQuery("select m from MemberV2 m", MemberV2.class)
                .getResultList();
        return list;
    }

    @Override
    public MemberV2 update(MemberV2 member, MemberDtoV2 updateParam) {
        MemberV2 updateMember = mapper.updateDtoToEntity(member, updateParam);
        em.persist(updateMember);
        return updateMember;
    }

    @Override
    public void delete(Long id) {
        MemberV2 member = findById(id);
        em.remove(member);
    }

    @Override
    public void clear() {
        em.createQuery("drop table MemberV2");
    }
}
