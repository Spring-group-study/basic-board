package com.study.board.repository;

import com.study.board.dto.PostDto;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.paging.Pagination;

import java.util.List;

public interface PostRepositoryV4 {
    public Long save(PostDtoV2 dto);

    public PostV2 findById(Long id);

    public List<PostV2> findAll();

    public List<PostV2> pagedFindAll(Pagination pagination);

    public int postCnt();

    public PostV2 update(PostV2 post, PostDtoV2 updateParam);

    public void delete(Long id);
}
