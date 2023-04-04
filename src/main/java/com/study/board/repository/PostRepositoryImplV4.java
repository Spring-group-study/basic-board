package com.study.board.repository;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.FileType;
import com.study.board.entity.MemberV2;
import com.study.board.entity.PostV2;
import com.study.board.entity.UploadFile;
import com.study.board.jpapaging.JpaPagingConst;
import com.study.board.login.session.SessionConst;
import com.study.board.mapper.MapperV5;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImplV4 implements PostRepositoryV4 {

    private final EntityManager em;
    private final MapperV5 mapper;

    @Override
    public Long save(PostDtoV2 dto, HttpServletRequest request) {
        PostV2 post = mapper.postSaveDtoToEntity(dto, request);
        em.persist(post);
        return post.getPostId();
    }

    @Override
    public void saveAttachFile(PostV2 post, UploadFile file) {
        em.persist(file);
        em.persist(post);
        file.setType(FileType.ATTACH);
        file.setPost(post);
    }

    @Override
    public void saveImageFiles(PostV2 post, List<UploadFile> files) {
        em.persist(post);
        for (UploadFile file : files) {
            em.persist(file);
            file.setType(FileType.IMAGE);
            file.setPost(post);
        }
    }

    @Override
    public PostV2 findByPostId(Long id) {
        PostV2 post = em.find(PostV2.class, id);
        return post;
    }

    @Override
    public List<PostV2> findAllPerPage(int page) {
        String jpql = "select p from PostV2 p order by p.postId desc";
        return em.createQuery(jpql, PostV2.class)
                .setFirstResult(JpaPagingConst.POST_CNT_PER_PAGE * (page-1))
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
        return mapper.postUpdateDtoToEntity(oldPost, updateParam);
    }

    @Override
    public Boolean accessValidation(Long postId, HttpServletRequest request) {
        MemberV2 sessionMember = mapper.getMemberFromSession(request);
        return !sessionMember.getPosts().stream().filter(post -> post.getPostId() == postId).findAny().isEmpty();
    }

    @Override
    public void delete(Long id) {
        PostV2 deletePost = em.find(PostV2.class, id);
        em.remove(deletePost);
    }

    @Override
    public void testSave(PostV2 post) {
        em.persist(post);
    }
}
