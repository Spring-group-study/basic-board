package com.study.board.repository;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV2;
import com.study.board.mapper.MapperV2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImplV2 implements PostRepositoryV2 {

    private static Map<Long, PostV2> postList = new HashMap<>();
    private static Long sequence = 0L;

    private MapperV2 mapper = new MapperV2();

    @Override
    public PostV2 save(PostDtoV2 dto) {
        PostV2 post = mapper.SaveDtoToEntity(dto);
        post.setId(++sequence);
        postList.put(post.getId(), post);
        return post;
    }

    @Override
    public PostV2 findById(Long id) {
        return postList.get(id);
    }

    @Override
    public List<PostV2> findAll() {
        return new ArrayList<>(postList.values());
    }

    @Override
    public PostV2 update(PostV2 post, PostDtoV2 updateParam) {   //dto적용할것
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
