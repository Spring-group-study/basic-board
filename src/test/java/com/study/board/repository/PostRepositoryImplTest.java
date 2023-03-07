package com.study.board.repository;

import com.study.board.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceV1ImplTest {

    @Autowired
    private PostRepositoryV1 postRepositoryV1;

    @Test
    public void 전체조회() throws Exception {
        //given

        //when
        List<Post> all = postRepositoryV1.findAll();

        //then
        assertThat(all.size()).isNotEqualTo(0);
    }
    @Test
    public void 한건_조회() throws Exception {
        //given
        Post testPost = new Post(15l, "테스트", "테스트", "테스트");
        postRepositoryV1.save(testPost);
        //when
        Post findPost = postRepositoryV1.findById(12l);
        //then
        assertThat(findPost.getAuthor()).isEqualTo(testPost.getAuthor());

    }
    @Test
    public void 데이터_수정() throws Exception {
        //given
        Post testPost = new Post();
        testPost.toEntity("테스트", "테스트", "테스트");
        postRepositoryV1.save(testPost);
        //when
        postRepositoryV1.update(12l,"테스트1","테스트2","테스트3");
        Post findPost = postRepositoryV1.findById(12l);
        //then
        assertThat(findPost.getAuthor()).isEqualTo("테스트1");
    }
    @Test
    public void 데이터_삭제() throws Exception {
        //given
        Post testPost = new Post();
        testPost.toEntity("테스트", "테스트", "테스트");
        postRepositoryV1.save(testPost);
        //when
        postRepositoryV1.delete(12l);
        //then
        assertThat(postRepositoryV1.findAll().size()).isEqualTo(11);
    }
}