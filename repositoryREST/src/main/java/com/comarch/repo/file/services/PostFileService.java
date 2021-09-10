package com.comarch.repo.file.services;

import com.comarch.repo.file.domain.FileConfig;
import com.comarch.repo.file.domain.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PostFileService {

    @Autowired
    FileServiceInterface fileServiceInterface;

    public File createFile(
            MultipartFile file,
            FileConfig fileConfig
    ){
        fileConfig.setDescription(fileConfig.getDescription().isEmpty() ? "None" : fileConfig.getDescription());
        fileConfig.setFormat(fileConfig.getFormat() == null ? file.getContentType() : fileConfig.getFormat());
        File addedFile = fileServiceInterface.storeFile(file,fileConfig);
        log.info("POST: " + addedFile);
        return addedFile;
    }
}
