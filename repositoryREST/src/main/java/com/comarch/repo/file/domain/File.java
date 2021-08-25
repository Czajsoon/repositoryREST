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
    protected String id;
    protected String fileName;
    protected String userFileName;
    protected String fileType;
    protected long fileSize;
    protected String description;
    protected Path path;
    protected String firstNameUser;
    protected String lastNameUser;
    protected String userID;
    protected List<String> permissionList = new ArrayList<>();

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
