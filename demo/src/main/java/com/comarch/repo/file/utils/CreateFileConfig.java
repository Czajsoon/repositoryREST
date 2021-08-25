package com.comarch.repo.file.utils;

import com.comarch.repo.file.domain.FileConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileConfig {
    public static void createConfigOfFile(FileConfig fileConfig)
            throws IOException {
        Path configPath;
        if(Files.deleteIfExists(Paths.get(fileConfig.getTargetLocation() + "file_conf.txt")));
            configPath = Files.createFile(Paths.get(fileConfig.getTargetLocation() + "file_conf.txt"));
        String configText = configContent(fileConfig);
        Files.write(configPath, configText.getBytes());
    }

    private static String configContent(FileConfig fileConfig){
        return  "ID:           " + fileConfig.getId() +
                "\nsize:         " + fileConfig.getFileSize() +
                "\nfileName:     " + fileConfig.getTargetLocation().getFileName() +
                "\nuserFileName: " + fileConfig.getFileName() +
                "\ndescription:  " + fileConfig.getDescription() +
                "\nformatFile:   "  + fileConfig.getFormat() +
                "\npathFile:     " + fileConfig.getTargetLocation().toAbsolutePath() +
                "\nfirstName:    " + fileConfig.getFirstName() +
                "\nlastName:     " + fileConfig.getLastName() +
                "\nuserID:       " + fileConfig.getUserID() +
                "\naccessUsers:  " + fileConfig.getPermissionList();
    }
}
