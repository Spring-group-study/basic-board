package com.study.board.repository;

import com.study.board.dto.PostDto;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.entity.UploadFile;
import com.study.board.paging.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostRepositoryV4 {
    public Long save(PostDtoV2 dto, HttpServletRequest request);

    public void saveAttachFile(PostV2 post, UploadFile file);

    public void saveImageFiles(PostV2 post, List<UploadFile> files);

    public PostV2 findByPostId(Long id);

    public List<PostV2> findAllPerPage(int page);

    public Integer postCnt();

    public PostV2 update(PostV2 post, PostDtoV2 updateParam);

    public Boolean accessValidation(Long postId, HttpServletRequest request);

    public void delete(Long id);

    public void testSave(PostV2 post);
}
