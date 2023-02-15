package com.study.board.repository;

import com.study.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private static final Map<Long, Post> posts = new HashMap<>();
    private static Long sequence = 0L;


    public Post save(Post post) {
        post.setId(++sequence);
        posts.put(post.getId(), post);
        return post;
    }

    public Post findPost(Long id) {
        return posts.get(id);
    }

    public List<Post> findAllPost() {
        return new ArrayList<>(posts.values());
    }

    public void updatePost(Long id, Post updatedPost) {
        Post findPost = findPost(id);
        findPost.setAuthor(updatedPost.getAuthor());
        findPost.setTitle(updatedPost.getTitle());
        findPost.setContent(updatedPost.getContent());
    }

    public void deletePost(Long id) {
        posts.remove(id);
    }

}
