package com.study.board.service;

import com.study.board.dto.PostDto;
import com.study.board.entity.Member;
import com.study.board.entity.Post;
import com.study.board.repository.LoginRepositoryV2;
import com.study.board.repository.PostRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV4 {
    private final PostRepositoryV3 postRepositoryV3;
    private final LoginRepositoryV2 loginRepositoryV2;

    public Page<Post> getAllPostByMemberIdPage(int pageSize, String memberId) {
        Pageable pageable = Pageable.ofSize(pageSize);
        return postRepositoryV3.findByMember_MemberId(memberId, pageable);
    }
    @Transactional
    public void savePostWithMemberId(PostDto dto, Long memberId) {
        Member findMember = loginRepositoryV2.findById(memberId).get();
        Post post = new Post(dto.getId(), findMember.getMemberId(), dto.getTitle(),dto.getContent(),findMember);
        postRepositoryV3.save(post);
    }
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepositoryV3.findById(id).get();

        postRepositoryV3.delete(post);
    }
    public Post getOnePost(Long id) {
        return postRepositoryV3.findById(id).get();
    }

    @Transactional
    public void updatePost(Long id, String author, String content, String title) {
        Post findPost = postRepositoryV3.findById(id).get();
        findPost.toEntity(author,content,title);
    }

    public Page<Post> getPostByPage(Pageable pageable) {
        return postRepositoryV3.findAll(pageable);
    }
}
