package com.study.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mem_id", nullable = false)
    private Long id;

    private String memberId;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;
}
