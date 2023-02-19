package com.study.board.config;

import com.study.board.entity.Post;
import com.study.board.service.PostServiceV1;
import com.study.board.service.PostServiceV2;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class dbInit {
    private final PostServiceV1 postServiceV1;
    private final PostServiceV2 postServiceV2;

    public dbInit(PostServiceV1 postServiceV1, PostServiceV2 postServiceV2) {
        this.postServiceV1 = postServiceV1;
        this.postServiceV2 = postServiceV2;
    }

    @PostConstruct
    public void Init() {
        for (int i = 0; i < 11; i++) {
            String title = "제목" + i;
            String author = "작성자" + i;
            String content = "내용" + i;
            Post post = new Post();
            post.toEntity(author, title, content);
            postServiceV1.savePost(post);
        }
    }

    @PostConstruct
    public void dbInit() {
        for (int i = 0; i < 6; i++) {
            String title = "제목" + i;
            String author = "작성자" + i;
            String content = "내용" + i;
            Post post = new Post();
            post.toEntity(author, title, content);
            postServiceV2.savePost(post);
        }
    }
}
