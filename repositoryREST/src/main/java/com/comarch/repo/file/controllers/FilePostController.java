package com.comarch.repo.file.controllers;

import com.comarch.repo.file.domain.FileConfig;
import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.services.FileServiceInterface;
import com.comarch.repo.file.services.PostFileService;
import com.comarch.repo.file.services.ReadAllRepositoryFilesService;
import com.comarch.repo.utils.Ids;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@Slf4j
public class FilePostController {
    @Autowired
    FileServiceInterface fileServiceInterface;

    @Autowired
    ReadAllRepositoryFilesService readRepositoryFilesService;

    @Autowired
    PostFileService postFileService;

    @PostMapping(value = "/createFile")
    public ResponseEntity<File> createFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "additionalPath", required = false) String additionalPath,
            @RequestParam(value = "format", required = false) String format,
            @RequestParam(value = "fileName") String fileName,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "userID") String userID
    ){
        format = format.isEmpty() ? file.getContentType() : format;
        description = description.isEmpty() ? "None" : description;
        File addedFile = fileServiceInterface.storeFile(file,FileConfig.builder()
                .fileName(fileName)
                .description(description)
                .format(format)
                .additionalPath(additionalPath)
                .firstName(firstName)
                .lastName(lastName)
                .userID(userID)
                .build());
        log.info("POST: " + addedFile);
        return ResponseEntity.ok()
                .body(addedFile);
    }

    @PostMapping(value = "/createFiles")
    public ResponseEntity<List<File>> createFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("description") String description,
            @RequestParam("additionalPath") String additionalPath,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("userID") String userID
    ){
        additionalPath = additionalPath.isEmpty() ? Ids.generateId() : additionalPath;
        List<File> uploadedList = new ArrayList<>();
        for(MultipartFile file: files)
            uploadedList.add(postFileService.createFile(file,FileConfig.builder()
                    .description(description)
                    .additionalPath(additionalPath)
                    .firstName(firstName)
                    .lastName(lastName)
                    .userID(userID)
                    .build()));
        return ResponseEntity.ok().body(uploadedList);
    }


}
