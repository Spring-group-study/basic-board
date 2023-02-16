package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImplV1 implements PostRepositoryV1 {

    private static Map<Long, Post> postList = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Post save(Post post) {
        //dto받고 entity로 변환 후 postList에 저장
        post.setPostId(++sequence);
        postList.put(post.getPostId(), post);

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

    //테스터용
    @Override
    public void clear() {
        postList.clear();
    }
}
