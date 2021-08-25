package com.comarch.repo.user.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadPaths {
    private static final Path directory = Paths.get("D:\\uploads\\repo");

    public static List<Path> readUserPathDir(String givenDir) throws IOException {
        List<Path> directories = Files.walk(Paths.get(directory + "/" + givenDir + '/'),1)
                            .filter(Files::isRegularFile)
                            .collect(Collectors.toList());
        List<Path> configFiles = new ArrayList<>();//filtry servlety nazy klasy if nulle, !if
        for (Path dir : directories) {
            if(dir.getFileName().toString().endsWith("_conf.txt")){
                configFiles.add(dir);
            }
        }
        return configFiles;
    }

}
