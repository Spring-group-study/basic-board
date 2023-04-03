package com.study.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    private String author;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    public String getMemberId() {
        if (member == null) {
            return null;
        }
        return member.getMemberId();
    }

    public void toEntity(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void savePostToEntity(String author, String title, String content, Member member) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
