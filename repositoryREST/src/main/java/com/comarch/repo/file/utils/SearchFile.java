package com.comarch.repo.file.utils;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.user.utils.ReadPaths;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFile {

//    private FileSharedService fileSharedService;

    private static List<File> searchFilesType(File thisFile, List<File> fileList){
        return thisFile.getFileType() != null
                ? fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getFileType().equals(thisFile.getFileType()))
                .collect(Collectors.toList())
                : null;
    }

    private static List<File> searchFilesName(File thisFile, List<File> fileList) {
        return thisFile.getFileName() != null
                ? (thisFile.getFileType() != null
                ? fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getFileName().equals(thisFile.getFileName()) &&
                        file.getFileType().equals(thisFile.getFileType()))
                .collect(Collectors.toList())
                : fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getFileName().equals(thisFile.getFileName()))
                .collect(Collectors.toList()))
                : searchFilesType(thisFile, fileList);
    }

    private static List<File> searchFilesID(File thisFile, List<File> fileList){
        return thisFile.getId() != null
                ? (thisFile.getFileName() != null
                ? (thisFile.getFileType() != null
                ? fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getId().equals(thisFile.getId()) &&
                        file.getFileName().equals(thisFile.getFileName()) &&
                        file.getFileType().equals(thisFile.getFileType()))
                .collect(Collectors.toList())
                : fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getId().equals(thisFile.getId()) &&
                        file.getFileName().equals(thisFile.getFileName()))
                .collect(Collectors.toList()))
                : (thisFile.getFileType() != null ? fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file)) &&
                        file.getId().equals(thisFile.getId()) &&
                        file.getFileType().equals(thisFile.getFileType()))
                .collect(Collectors.toList())
                : fileList.stream()
                .filter(file -> (file.getUserID().equals(thisFile.getUserID()) || getFileShared(thisFile.getUserID(),file))&&
                        file.getId().equals(thisFile.getId()))
                .collect(Collectors.toList())))
                : searchFilesName(thisFile, fileList);
    }

    public static List<File> searchFiles(File thisFile, String dirPath, List<File> allFilesList) throws IOException {
        List<File> filesList = new ArrayList<>();
        if(dirPath != null){
            for (Path dir: ReadPaths.readUserPathDir(dirPath))
                filesList.add(ReadFileConfig.readConfigFile(dir));
            return thisFile.getId() != null || thisFile.getFileName() != null || thisFile.getFileType() != null
                    ? searchFilesID(thisFile,filesList)
                    : filesList;
        }
        else
            return searchFilesID(thisFile, allFilesList);
    }

    public static boolean getFileShared(String userID, File file){
        return file.getPermissionList()
                .stream()
                .filter(usersIds -> usersIds.equals(userID))
                .collect(Collectors.toList()).size() > 0;
    }
}
