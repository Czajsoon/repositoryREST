package com.comarch.repo.file.domain;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class FileConfig{
    private Path targetLocation;
    private String fileName;
    private String description;
    private String format;
    private String additionalPath;
    private String id;
    private String firstName;
    private String lastName;
    private String userID;
    private String permissionList;
    private String fileSize;
}
