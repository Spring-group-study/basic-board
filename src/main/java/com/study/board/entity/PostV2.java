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
    private List<UploadFile> files = new ArrayList<>();

    public UploadFile getAttachFile() {
        for (UploadFile file : files) {
            if (file.getType() == FileType.ATTACH) {
                return file;
            }
        }
        return null;
    }

    public List<UploadFile> getImageFiles() {
        List<UploadFile> rs = new ArrayList<>();
        for (UploadFile file : files) {
            if (file.getType() == FileType.IMAGE) {
                rs.add(file);
            }
        }
        return rs;
    }

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

}
