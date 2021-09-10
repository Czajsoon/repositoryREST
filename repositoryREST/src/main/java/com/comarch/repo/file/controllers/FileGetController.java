package com.comarch.repo.file.controllers;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.services.FileServiceInterface;
import com.comarch.repo.file.services.ReadAllRepositoryFilesService;
import com.comarch.repo.file.utils.SearchFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class FileGetController {

    @Autowired
    FileServiceInterface fileServiceInterface;

    @Autowired
    ReadAllRepositoryFilesService readRepositoryFilesService;

    @GetMapping(value = "/adminGetIdFile/{id}")
    public ResponseEntity<List<File>> getFileAdmin(@PathVariable String id){
        log.info("GET: " + fileServiceInterface.getFile(id));
        return ResponseEntity.ok()
                .body(Arrays.asList(fileServiceInterface.getFile(id)));
    }

    @GetMapping(value = "/adminAllFiles")
    public ResponseEntity<List<File>> getAllFiles(){
        log.info("GET: " + fileServiceInterface.getAllFiles());
        return ResponseEntity.ok()
                .body(fileServiceInterface.getAllFiles());
    }

    @GetMapping(value = "/file/{id}")
    public ResponseEntity<List<File>> getFiles(@PathVariable String id){
        log.info("GET: " + fileServiceInterface.getAllFilesUser(id));
        return ResponseEntity.ok()
                .body(fileServiceInterface.getAllFilesUser(id));
    }//exception handler

    @GetMapping(value = "/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id, HttpServletRequest request) throws IOException {
        Resource resource = fileServiceInterface.loadFileAsResource(id);
        log.info("GET FILE: " + id);
        String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        contentType = contentType==null ? "application/octet-stream" : contentType;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/searchFile")
    public ResponseEntity<List<File>> searchFiles(@RequestParam(value = "userID") String userID,
                                                  @RequestParam(value = "dir", required = false) String directory,
                                                  @RequestParam(value = "fileID", required = false) String fileID,
                                                  @RequestParam(value = "fileName",required = false) String fileName,
                                                  @RequestParam(value = "format", required = false) String format) throws IOException {
        List<File> files = SearchFile.searchFiles(File.builder()
                .userID(userID)
                .id(fileID)
                .fileName(fileName)
                .fileType(format)
                .build(), directory, fileServiceInterface.getAllFiles());
        log.info("GET: " + files);
        return files.size() > 0 ? ResponseEntity
                .ok()
                .body(files) : null;
    }
}
