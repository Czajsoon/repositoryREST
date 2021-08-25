package com.comarch.repo.file.services;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.repository.InMemoryRepositoryFileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileDeleteService {

    @Autowired
    InMemoryRepositoryFileInterface inMemoryRepositoryFileInterface;

    public File deleteFile(String id){
        return inMemoryRepositoryFileInterface.deleteFile(id);
    }

    public void deleteFileFromDirectory(File file) throws IOException {
        Files.deleteIfExists(file.getPath());
        if(Files.exists(Path.of(file.getPath() + "files_conf.txt"))){
            Files.deleteIfExists(Path.of(file.getPath() + "files_conf.txt"));
        }
        else{
            Files.deleteIfExists(Path.of(file.getPath() + "file_conf.txt"));
        }
    }

}
