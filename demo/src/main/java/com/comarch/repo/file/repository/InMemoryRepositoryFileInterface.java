package com.comarch.repo.file.repository;

import com.comarch.repo.file.domain.File;

import java.nio.file.Path;
import java.util.Map;

public interface InMemoryRepositoryFileInterface {
    Map<String, File> getAllFilesList();

    File getFile(String id, String userID);

    File getFile(String id);

    void addFile(String fileName, Path file, String fileType);

    File addFile(String id, String fileName, Path file, String fileType);

    Path getFilePath(String id);

    File deleteFile(String id);

    File addFile(File file);

}
