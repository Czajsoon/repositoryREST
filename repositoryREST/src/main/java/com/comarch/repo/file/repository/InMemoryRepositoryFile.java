package com.comarch.repo.file.repository;


import com.comarch.repo.file.domain.File;
import com.comarch.repo.utils.Ids;
import com.comarch.repo.file.services.ReadAllRepositoryFilesService;
import com.comarch.repo.file.utils.ReadFileConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


@Repository
public class InMemoryRepositoryFile implements InMemoryRepositoryFileInterface {

    @Autowired
    ReadAllRepositoryFilesService readAllRepositoryFilesService;

    private Map<String, File> filesList = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Map<String, File> getAllFilesList() {
        return filesList;
    }

    @Override
    public File getFile(String id, String userID){
        if(filesList.get(id) != null)
            if(filesList.get(id).getUserID().equals(userID))
                return filesList.get(id);
            else return null;
        else
            return null;
    }

    public File deleteFile(String id){
        return filesList.remove(id);
    }

    @Override
    public File getFile(String id){
        return filesList.get(id);
    }

    @Override
    public Path getFilePath(String id){
        return filesList.get(id).getPath();
    }

    @Override
    public void addFile(String fileName, Path file, String fileType){
        String id = Ids.generateId();
        filesList.put(id, File.builder()
                                .id(id)
                                .fileName(fileName)
                                .path(file)
                                .fileType(fileType)
                                .build());
    }

    @Override
    public File addFile(String id, String fileName, Path file, String fileType){
        return filesList.put(id, File.builder().id(id).fileName(fileName).path(file).fileType(fileType).build());
    }

    @Override
    public File addFile(File file) {
        filesList.put(file.getId(),file);
        return filesList.get(file.getId());
    }


    @PostConstruct
    public void init() throws IOException {
        List<Path> configFiles = readAllRepositoryFilesService.ReadFiles();
        for(Path dir : configFiles){
            addFile(ReadFileConfig.readConfigFile(dir));
        }
    }
}
