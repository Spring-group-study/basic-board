package com.study.board.file;

import com.study.board.entity.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + "filename";
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> rs = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                rs.add(storeFile(multipartFile));
            }
        }
        return rs;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFileName, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = createExt(originalFileName);
        return uuid + "." + ext;
    }

    private String createExt(String originalFileName) {
        int position = originalFileName.lastIndexOf(".");
        return originalFileName.substring(position + 1);
    }


}
