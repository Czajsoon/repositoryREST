package com.comarch.repo.file.controllers;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.services.FileServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin("http://localhost:4200/")
@RestController
@Slf4j
public class FilePutController {

    @Autowired
    FileServiceInterface fileServiceInterface;

    @PutMapping(value = "/giveOrRemovePermission")
    public ResponseEntity<File> permissionAccess(@RequestParam("fileID") String fileID,
                                                 @RequestParam("userID") String userID) throws IOException {
        File file = fileServiceInterface.addOrRemoveAccess(fileID,userID);
        log.info("PUT: " + file);
        return ResponseEntity.ok()
                .body(file);
    }

    @PutMapping(value = "/giveAccessToFolder")
    public ResponseEntity<String> permissionAccessToFolder(@RequestParam("directory") String directory,
                                                   @RequestParam("userID") String userID){

        return null;
    }
}
