package com.study.board.repository;

import com.study.board.entity.PostV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryPostRepositoryV1Test {

    private PostRepositoryV1 postRepositoryV1 = new PostRepositoryImplV1();

    @BeforeEach
    void clear() {
        postRepositoryV1.clear();
    }

    @Test
    void save() {
        PostV1 post = new PostV1("김현수","TestTitle","TestContent");
        PostV1 savedPost = postRepositoryV1.save(post);

        Assertions.assertThat(savedPost.getAuthor()).isEqualTo(post.getAuthor());
        Assertions.assertThat(savedPost.getTitle()).isEqualTo(post.getTitle());
        Assertions.assertThat(savedPost.getContent()).isEqualTo(post.getContent());
    }

    @Test
    void findById() {
        PostV1 post = new PostV1("김현수","TestTitle","TestContent");
        PostV1 savedPost = postRepositoryV1.save(post);
        PostV1 findPost = postRepositoryV1.findById(savedPost.getId());

        Assertions.assertThat(savedPost).isEqualTo(findPost);
    }

    @Test
    void findAll() {
        PostV1 post1 = new PostV1("김현수","TestTitle","TestContent");
        PostV1 post2 = new PostV1("김현수2","TestTitle2","TestContent2");
        PostV1 savedPost = postRepositoryV1.save(post1);
        PostV1 savedPost2 = postRepositoryV1.save(post2);
        Assertions.assertThat(savedPost).isIn(postRepositoryV1.findAll());
        Assertions.assertThat(savedPost2).isIn(postRepositoryV1.findAll());
    }

    @Test
    void update() {
        PostV1 post = new PostV1("김현수","TestTitle","TestContent");
        PostV1 savedPost = postRepositoryV1.save(post);

        PostV1 update = new PostV1("김현수2", "제목2", "내용2");
        PostV1 updatedPost = postRepositoryV1.update(post, update);

        Assertions.assertThat(update.getAuthor()).isEqualTo(updatedPost.getAuthor());
        Assertions.assertThat(update.getTitle()).isEqualTo(updatedPost.getTitle());
        Assertions.assertThat(update.getContent()).isEqualTo(updatedPost.getContent());
    }

    @Test
    void delete() {
        PostV1 post = new PostV1("김현수","TestTitle","TestContent");
        PostV1 savedPost = postRepositoryV1.save(post);

        postRepositoryV1.delete(savedPost.getId());
        Assertions.assertThat(savedPost).isNotIn(postRepositoryV1.findAll());
    }
}