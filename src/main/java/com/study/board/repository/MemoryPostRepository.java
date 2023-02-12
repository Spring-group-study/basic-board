package com.study.board.repository;

import com.study.board.domain.Post;
import com.study.board.dto.PostSaveDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryPostRepository implements PostRepository{

    public static Map<Long, Post> PostList = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Post post) {
        post.setId(++sequence);
        PostList.put(post.getId(), post);
    }

    @Override
    public Post findById(Long id) {
        return PostList.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(PostList.values());
    }

    @Override
    public Post update(Post post, PostSaveDto updateParam) {
        post.setAuthor(updateParam.getAuthor());
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    @Override
    public void delete(Long id) {
        PostList.remove(id);
    }
}
