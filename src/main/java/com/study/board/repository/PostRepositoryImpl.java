package com.study.board.repository;

import com.study.board.entity.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostRepositoryImpl implements PostRepository{

    private static Long id = 0L;
    private final Map<Long, Post> map = new HashMap<>();

    @Override
    public Post save(Post post) {
        map.put(post.getId(),post);
        return map.get(post.getId());
    }

    @Override
    public Post findOne(long postId) {
        return map.get(postId);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void delete(long postId) {
        map.remove(postId);
    }
}
