package com.study.board.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PostV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberV2 member;

    @OneToMany(mappedBy = "post")
    private List<UploadFile> imageFiles = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="file_id")
    private UploadFile attachFile;

    private String title;
    private String content;
    private LocalDateTime createTime=LocalDateTime.now();




    public PostV2() {
    }

    public PostV2(MemberV2 member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public PostV2(List<UploadFile> imageFiles, UploadFile attachFile) {
        this.imageFiles = imageFiles;
        this.attachFile = attachFile;
    }
}
