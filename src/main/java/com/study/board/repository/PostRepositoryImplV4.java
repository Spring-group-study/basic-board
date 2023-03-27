package com.study.board.repository;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV2;
import com.study.board.jpapaging.JpaPagingConst;
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
    public List<PostV2> findAllPerPage(int page) {
        String jpql = "select p from PostV2 p order by p.postId desc";
        return em.createQuery(jpql, PostV2.class)
                .setFirstResult(JpaPagingConst.POST_CNT_PER_PAGE * page)
                .setMaxResults(JpaPagingConst.POST_CNT_PER_PAGE)
                .getResultList();
    }

    @Override
    public Integer postCnt() {
        return em.createQuery("select p from PostV2 p", PostV2.class).getResultList().size();
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
