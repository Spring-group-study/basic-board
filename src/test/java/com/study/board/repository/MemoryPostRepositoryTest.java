package com.study.board.repository;

import com.study.board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPostRepositoryTest {

    private PostRepository postRepository = new MemoryPostRepository();

    @BeforeEach
    void clear() {
        postRepository.clear();
    }

    @Test
    void save() {
        Post post = new Post("김현수","TestTitle","TestContent");
        Post savedPost = postRepository.save(post);

        Assertions.assertThat(savedPost.getAuthor()).isEqualTo(post.getAuthor());
        Assertions.assertThat(savedPost.getTitle()).isEqualTo(post.getTitle());
        Assertions.assertThat(savedPost.getContent()).isEqualTo(post.getContent());
    }

    @Test
    void findById() {
        Post post = new Post("김현수","TestTitle","TestContent");
        Post savedPost = postRepository.save(post);
        Post findPost = postRepository.findById(savedPost.getId());

        Assertions.assertThat(savedPost).isEqualTo(findPost);
    }

    @Test
    void findAll() {
        Post post1 = new Post("김현수","TestTitle","TestContent");
        Post post2 = new Post("김현수2","TestTitle2","TestContent2");
        Post savedPost = postRepository.save(post1);
        Post savedPost2 = postRepository.save(post2);
        Assertions.assertThat(savedPost).isIn(postRepository.findAll());
        Assertions.assertThat(savedPost2).isIn(postRepository.findAll());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}