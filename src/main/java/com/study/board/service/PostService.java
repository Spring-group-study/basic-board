package com.study.board.service;

import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepositoryImpl postRepository;

    public PostService(PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post post, long postId) {
        Post findPost = postRepository.findOne(postId);
        return postRepository.save(findPost);
    }

    public Post findPost(long postId) {
        Post findPost = postRepository.findOne(postId);
        return findPost;
    }

    public List<Post> findPostList(long postId) {
        return postRepository.findAll();
    }

    public void deletePost(long postId) {
        postRepository.delete(postId);
    }

}
