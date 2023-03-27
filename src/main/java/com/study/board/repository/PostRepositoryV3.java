package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryV3 extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
}
