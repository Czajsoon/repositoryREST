package com.comarch.repo.file.domain;

import com.comarch.repo.file.utils.CreateFileConfig;
import com.comarch.repo.file.utils.SaveFileConfigAccess;
import lombok.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class File {
    private String id;
    private String fileName;
    private String userFileName;
    private String fileType;
    private long fileSize;
    private String description;
    private Path path;
    private String firstNameUser;
    private String lastNameUser;
    private String userID;
    private List<String> permissionList = new ArrayList<>();

    public void putPermissionUser(String userID) throws IOException {
        this.permissionList.add(userID);
        CreateFileConfig.createConfigOfFile(FileConfig.builder()
                .targetLocation(path)
        .fileName(fileName)
        .description(description)
        .format(fileType)
        .id(id)
        .firstName(firstNameUser)
        .lastName(lastNameUser)
        .userID(this.userID)
        .permissionList(SaveFileConfigAccess.savePermissions(permissionList))
        .fileSize(String.valueOf(fileSize))
        .build());
    }

    public void removePermission(String userID) throws IOException {
        this.permissionList.remove(userID);
        CreateFileConfig.createConfigOfFile(FileConfig.builder()
                .targetLocation(path)
                .fileName(fileName)
                .description(description)
                .format(fileType)
                .id(id)
                .firstName(firstNameUser)
                .lastName(lastNameUser)
                .userID(this.userID)
                .permissionList(SaveFileConfigAccess.savePermissions(permissionList))
                .fileSize(String.valueOf(fileSize))
                .build());
    }
}
