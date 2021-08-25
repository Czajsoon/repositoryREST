package com.comarch.repo.file.services;

import com.comarch.repo.file.domain.FileConfig;
import com.comarch.repo.file.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileServiceInterface {

    Resource loadFileAsResource(String id);

    List<File> getAllFiles();

    File getFile(String id);

    File storeFile(MultipartFile file, FileConfig fileConfig);

    File addOrRemoveAccess(String fileID, String userID) throws IOException;

    List<File> getAllFilesUser(String userID);

    File getFile(String id, String userID);
}
