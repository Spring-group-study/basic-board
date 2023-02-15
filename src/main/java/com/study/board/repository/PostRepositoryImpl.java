package com.study.board.repository;

import com.study.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository{

    private static Map<Long, Post> postList = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        postList.put(post.getId(), post);

        return post;
    }

    @Override
    public Post findById(Long id) {
        return postList.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postList.values());
    }

    @Override
    public Post update(Post post, Post updateParam) {   //dto적용할것
        post.setAuthor(updateParam.getAuthor());
        post.setTitle(updateParam.getTitle());
        post.setContent(updateParam.getContent());
        return post;
    }

    @Override
    public void delete(Long id) {
        postList.remove(id);
    }
}
