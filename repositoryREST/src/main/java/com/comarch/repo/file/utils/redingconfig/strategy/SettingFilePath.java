package com.comarch.repo.file.utils.redingconfig.strategy;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.FileSetter;

import java.nio.file.Paths;

public class SettingFilePath implements FileSetter {
    @Override
    public File setter(String line, File file) {
        file.setPath(Paths.get(line));
        return file;
    }
}
