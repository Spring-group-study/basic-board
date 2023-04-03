package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
public class FileDto {

    private Long fileId;
    private String fileName;
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
