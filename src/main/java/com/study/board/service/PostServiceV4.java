package com.study.board.service;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.entity.UploadFile;
import com.study.board.repository.PostJpaRepository;
import com.study.board.repository.PostRepositoryV4;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceV4 {

    private final PostRepositoryV4 postRepository;
    private final PostJpaRepository jpaRepository;

    public Long save(PostDtoV2 dto, HttpServletRequest request) {
        return postRepository.save(dto,request);
    }

    public void saveAttachFile(PostV2 post, UploadFile file) {
        postRepository.saveAttachFile(post,file);
    }

    public void saveImageFiles(PostV2 post, List<UploadFile> files) {
        postRepository.saveImageFiles(post, files);
    }

    public PostV2 findById(Long id) {
        return postRepository.findByPostId(id);
    }

    //    public List<PostV2> findAllByPage(int page) {
//        return postRepository.findAllPerPage(page);
//    }
    public List<PostV2> findAllByPage(Pageable pageable) {
        return jpaRepository.findAll(pageable).getContent();
    }

    public Integer postCnt() {
        return postRepository.postCnt();
    }

    public PostV2 update(PostV2 post, PostDtoV2 updateParam) {
        return postRepository.update(post, updateParam);
    }

    public Boolean isAuthor(Long postId, HttpServletRequest request) {
        return postRepository.accessValidation(postId,request);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }

    public List<PostV2> findByKeyword(String keyword) {
        List<PostV2> result1 = jpaRepository.findByContentContaining(keyword);
        List<PostV2> result2 = jpaRepository.findByTitleContaining(keyword);
        List<PostV2> result = new ArrayList<>();
        result.addAll(result1);
        result.addAll(result2);
        return result;
    }
    //init메서드용 save
    public void testSave(PostV2 post) {
        postRepository.testSave(post);
    }
}
