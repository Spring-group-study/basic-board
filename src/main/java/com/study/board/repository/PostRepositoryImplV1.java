package com.study.board.repository;

import com.study.board.domain.PostV1;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImplV1 implements PostRepositoryV1 {

    private static Map<Long, PostV1> postList = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public PostV1 save(PostV1 post) {
        post.setId(++sequence);
        postList.put(post.getId(), post);

        return post;
    }

    @Override
    public PostV1 findById(Long id) {
        return postList.get(id);
    }

    @Override
    public List<PostV1> findAll() {
        return new ArrayList<>(postList.values());
    }

    @Override
    public PostV1 update(PostV1 post, PostV1 updateParam) {   //dto적용할것
        post.setAuthor(updateParam.getAuthor());
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    @Override
    public void delete(Long id) {
        postList.remove(id);
    }

    //테스터용
    @Override
    public void clear() {
        postList.clear();
    }
}
