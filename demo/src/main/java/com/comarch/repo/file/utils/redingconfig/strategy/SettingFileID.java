package com.comarch.repo.file.utils.redingconfig.strategy;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.FileSetter;

public class SettingFileID implements FileSetter {
    @Override
    public File setter(String line, File file) {
        file.setId(line);
        return file;
    }
}

