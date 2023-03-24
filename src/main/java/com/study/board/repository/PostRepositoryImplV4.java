package com.study.board.repository;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV2;
import com.study.board.mapper.MapperV5;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImplV4 implements PostRepositoryV4 {

    private final EntityManager em;
    private final MapperV5 mapper;

    @Override
    public Long save(PostDtoV2 dto) {
        PostV2 post = mapper.SaveDtoToEntity(dto);
        em.persist(post);
        return post.getPostId();
    }

    @Override
    public PostV2 findById(Long id) {
        PostV2 post = em.find(PostV2.class, id);
        return post;
    }

    @Override
    public List<PostV2> findAll() {
        String jpql = "select p from PostV2 p";
        return em.createQuery(jpql, PostV2.class).getResultList();
    }

    //페이징 기능 구현해야함 -> querydsl...
    @Override
    public List<PostV2> pagedFindAll(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public int postCnt() {
        return 0;
    }

    @Override
    public PostV2 update(PostV2 post, PostDtoV2 updateParam) {
        PostV2 oldPost = em.find(PostV2.class, post.getPostId());
        return mapper.updateDtoToEntity(oldPost, updateParam);
    }

    @Override
    public void delete(Long id) {
        PostV2 deletePost = em.find(PostV2.class, id);
        em.remove(deletePost);
    }
}
