package com.comarch.repo.file.utils.redingconfig;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.strategy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileFactory {
    static Map<String, FileSetter> operationsMap = new HashMap<>();
    static {
        operationsMap.put("ID:           ",new SettingFileID());
        operationsMap.put("size:         ",new SettingFileFileSize());
        operationsMap.put("fileName:     ",new SettingFileFileName());
        operationsMap.put("userFileName: ",new SettingFileUserFileName());
        operationsMap.put("description:  ",new SettingFileDescription());
        operationsMap.put("formatFile:   ",new SettingFileFileType());
        operationsMap.put("pathFile:     ",new SettingFilePath());
        operationsMap.put("firstName:    ",new SettingFileFirstNameUser());
        operationsMap.put("lastName:     ",new SettingFileLastNameUser());
        operationsMap.put("userID:       ",new SettingFileUserID());
        operationsMap.put("accessUsers:  ",new SettingFilePermissionList());
    }//beanio

    public static Optional<FileSetter> getOperation(String operation){
        return Optional.ofNullable(operationsMap.get(operation));
    }

    public File setFile(String line,File file){
        FileSetter fileSetter = FileFactory.getOperation(line.substring(0,14))
                .orElseThrow(() -> new IllegalArgumentException("Invalid argument!"));
        return fileSetter.setter(line.substring(14),file);
    }

}
