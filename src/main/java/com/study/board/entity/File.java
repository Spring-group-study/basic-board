package com.study.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "FILE_ID")
    private Long id;
    private String fileName;
    @Embedded
    private UploadFile attachFile;

    @ElementCollection
    @CollectionTable(name = "IMAGES", joinColumns = @JoinColumn(name = "FILE_ID"))
    private List<UploadFile> imageFiles = new ArrayList<>();

    public void toFile(String fileName, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.fileName = fileName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}
