package com.comarch.repo.file.utils;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SaveFileConfigAccess {

    public static String savePermissions(List<String> permissionList) {
        String access = "";
        for(String userID: permissionList){
            access = access.concat(userID +",");
        }
        return access;
    }
}
