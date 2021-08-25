package com.comarch.repo.file.services;

import com.comarch.repo.file.repository.InMemoryRepositoryFileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadAllRepositoryFilesService {

    @Autowired
    InMemoryRepositoryFileInterface inMemoryRepositoryFileInterface;

    private final Path directory = Paths.get("D:\\uploads\\repo");

    public List<Path> ReadFiles(){
        List<Path> directories = new ArrayList<>();
        try {
            directories =
                    Files.walk(directory,10)
                            .filter(Files::isRegularFile)
                            .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Path> configFiles = new ArrayList<>();
        for (Path dir : directories) {
            if(dir.getFileName().toString().endsWith("_conf.txt")){
                configFiles.add(dir);
            }
        }
        return configFiles;
    }
}
