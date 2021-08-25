package com.comarch.repo.file.utils.redingconfig.strategy;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.FileSetter;

import java.util.ArrayList;
import java.util.List;

public class SettingFilePermissionList implements FileSetter {
    @Override
    public File setter(String line, File file) {
        file.setPermissionList(accessList(line));
        return file;
    }

    private List<String> accessList(String string) {
        List<String> permissionList = new ArrayList<>();
        StringBuilder userID = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (c == ',') {
                permissionList.add(userID.toString());
                userID = new StringBuilder();
            } else
                userID.append(c);
        }
        return permissionList;
    }
}
