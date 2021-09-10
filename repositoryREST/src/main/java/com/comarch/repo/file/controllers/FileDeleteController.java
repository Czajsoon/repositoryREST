package com.comarch.repo.file.controllers;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.services.FileDeleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@Slf4j
public class FileDeleteController {

    @Autowired
    FileDeleteService fileDeleteService;

    @DeleteMapping(value = "/deleteFile/{id}")
    public ResponseEntity<File> deleteFile(@PathVariable String id) throws IOException {
        File deleteFile = fileDeleteService.deleteFile(id);
        fileDeleteService.deleteFileFromDirectory(deleteFile);
        log.info("DELETE: [ " + deleteFile + "]");
        return ResponseEntity.ok()
                .body(deleteFile);
    }
}
