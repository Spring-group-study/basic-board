package com.study.board.repository;

import com.study.board.entity.MyPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@SpringBootTest
class MyPostRepositoryV3V2ImplTest {
    @Autowired PostRepositoryV2 repositoryV2;

    @BeforeEach
    public void dbClear() {
        repositoryV2.deleteAll();
    }

    @Test
    public void 전체조회() throws Exception {
        //given
        MyPost myPost1 = new MyPost(10l,"테스트","테스트","테스트");
        MyPost myPost2 = new MyPost(11l,"테스트","테스트","테스트");
        MyPost myPost3 = new MyPost(12l,"테스트","테스트","테스트");
        repositoryV2.savePost(myPost1);
        repositoryV2.savePost(myPost2);
        repositoryV2.savePost(myPost3);
        //when
        List<MyPost> myPostList = repositoryV2.findAll();
        //then
        assertThat(myPostList.size()).isEqualTo(9);
    }
    @Transactional
    @Test
    //테스트 시 테스트데이터를 생성해서 테스트코드를 실행할 시
    // postconstruct로 생성되는 객체를 없애줘야 id값 자동생성에서 에러가 나지 않는다(id제약조건)
    public void 한건조회_및_프록시_테스트() throws Exception {
        //given
        MyPost myPost = new MyPost();
        myPost.setAuthor("테스트");
        myPost.setContent("test");
        myPost.setTitle("test");
        Long saveId = repositoryV2.save(myPost);
        //when
        MyPost findMyPost = repositoryV2.findById(saveId);
        //then
        System.out.println("findPost = " + findMyPost);
        System.out.println("post = " + myPost);
        assertThat(myPost).isNotSameAs(findMyPost);
    }
}