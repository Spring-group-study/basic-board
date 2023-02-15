package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.mapper.PostMapper;
import com.study.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    public ResponseEntity createPost(@Valid @RequestBody PostDto.Post dto) {
        Post post = postMapper.postDtoToPost(dto);
        Post response = postService.createPost(post);
        return new ResponseEntity(postMapper.postToPostResponseDto(response), HttpStatus.CREATED);
    }

    @GetMapping("{post-id}")
    public ResponseEntity getPost(@PathVariable("post-id") @Positive long postId,
                                   @Valid @RequestBody PostDto.Patch dto) {
        Post response = postService.findPost(postId);
        return new ResponseEntity(postMapper.postToPostResponseDto(response), HttpStatus.OK);
    }

    @PatchMapping("{post-id}")
    public ResponseEntity patchPost(@PathVariable("post-id") @Positive long postId,
                                    @Valid @RequestBody PostDto.Patch dto) {
        Post post = postMapper.postPatchDtoToPost(dto);
        Post response = postService.updatePost(post, postId);
        return new ResponseEntity(postMapper.postToPostResponseDto(response), HttpStatus.OK);
    }

    @DeleteMapping("{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") @Positive long postId) {
        postService.deletePost(postId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}