package com.study.board.repository;

import com.study.board.dto.MemberDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.mapper.MapperV5;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImplV2 implements MemberRepositoryV2 {

    private final EntityManager em;
    private final MapperV5 mapper;

    @Override
    public Long save(MemberDtoV2 dto) {
        MemberV2 member = mapper.memberSaveDtoToEntity(dto);
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
        MemberV2 updateMember = mapper.memberUpdateDtoToEntity(member, updateParam);
        em.persist(updateMember);
        return updateMember;
    }

    @Override
    public void delete(Long id) {
        MemberV2 member = em.find(MemberV2.class, id);
        String jpql = "delete from PostV2 where member_id=:id ";
        em.createQuery(jpql).setParameter("id",id).executeUpdate();
        em.remove(member);      //순서 중요 : 멤버 먼저 삭제하면 post 의 fk 제약조건 위반 오류 뜸
    }

    @Override
    public void clear() {
        em.createQuery("drop table MemberV2");
    }
}
