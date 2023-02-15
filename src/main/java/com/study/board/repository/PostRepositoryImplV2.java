package com.study.board.repository;

import com.study.board.dto.PostDtoV1;
import com.study.board.entity.PostV1;
import com.study.board.mapper.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImplV2 implements PostRepositoryV2 {

    private static Map<Long, PostV1> postList = new HashMap<>();
    private static Long sequence = 0L;

    private Mapper mapper = new Mapper();

    @Override
    public PostV1 save(PostDtoV1 dto) {
        PostV1 post = mapper.SaveDtoToEntity(dto);
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
    public PostV1 update(PostV1 post, PostDtoV1 updateParam) {   //dto적용할것
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
