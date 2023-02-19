package com.study.board.repository;

import com.study.board.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryV2ImplTest {
    @Autowired PostRepositoryV2 repositoryV2;

    @Test
    public void 전체조회() throws Exception {
        //given
        Post post1 = new Post(10l,"테스트","테스트","테스트");
        Post post2 = new Post(11l,"테스트","테스트","테스트");
        Post post3 = new Post(12l,"테스트","테스트","테스트");
        repositoryV2.savePost(post1);
        repositoryV2.savePost(post2);
        repositoryV2.savePost(post3);
        //when
        List<Post> postList = repositoryV2.findAll();
        //then
        assertThat(postList.size()).isEqualTo(9);
    }
    @Test
    //테스트 시 테스트데이터를 생성해서 테스트코드를 실행할 시
    // postconstruct로 생성되는 객체를 없애줘야 id값 자동생성에서 에러가 나지 않는다(id제약조건)
    public void 한건조회() throws Exception {
        //given
        Post post = new Post();
        post.setAuthor("테스트");
        post.setContent("test");
        post.setTitle("test");
        Long saveId = repositoryV2.save(post);
        //when
        Post findPost = repositoryV2.findById(saveId);
        //then
        assertThat(post).isNotSameAs(findPost);
    }
}