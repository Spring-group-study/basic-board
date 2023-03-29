package com.study.board.repository;

import com.study.board.entity.PostV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostV2, Long> {

    public Page<PostV2> findAll(Pageable pageable);

    public List<PostV2> findByTitleContaining(String keyword);

    public List<PostV2> findByContentContaining(String keyword);
}
