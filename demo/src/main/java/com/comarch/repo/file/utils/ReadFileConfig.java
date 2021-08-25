package com.comarch.repo.file.utils;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.FileFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class ReadFileConfig {
    public static File readConfigFile(Path filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath.toAbsolutePath().toString()));
        String line;
        FileFactory fileFactory = new FileFactory();
        File file = new File();
        while ((line = br.readLine()) != null) {
            fileFactory.setFile(line,file);
        }
        return file;
    }
}
