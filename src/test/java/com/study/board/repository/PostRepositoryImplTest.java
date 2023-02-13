package com.study.board.repository;

import com.study.board.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 전체조회() throws Exception {
        //given

        //when
        List<Post> all = postRepository.findAll();

        //then
        assertThat(all.size()).isNotEqualTo(0);
    }
    @Test
    public void 한건_조회() throws Exception {
        //given
        Post testPost = new Post(15l, "테스트", "테스트", "테스트");
        postRepository.save(testPost);
        //when
        Post findPost = postRepository.findById(12l);
        //then
        assertThat(findPost.getAuthor()).isEqualTo(testPost.getAuthor());

    }
    @Test
    public void 데이터_수정() throws Exception {
        //given
        Post testPost = new Post();
        testPost.toEntity("테스트", "테스트", "테스트");
        postRepository.save(testPost);
        //when
        postRepository.update(12l,"테스트1","테스트2","테스트3");
        Post findPost = postRepository.findById(12l);
        //then
        assertThat(findPost.getAuthor()).isEqualTo("테스트1");
    }
    @Test
    public void 데이터_삭제() throws Exception {
        //given
        Post testPost = new Post();
        testPost.toEntity("테스트", "테스트", "테스트");
        postRepository.save(testPost);
        //when
        postRepository.delete(12l);
        //then
        assertThat(postRepository.findAll().size()).isEqualTo(11);
    }
}