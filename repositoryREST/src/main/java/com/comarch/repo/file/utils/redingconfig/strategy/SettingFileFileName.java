package com.comarch.repo.file.utils.redingconfig.strategy;

import com.comarch.repo.file.domain.File;
import com.comarch.repo.file.utils.redingconfig.FileSetter;

public class SettingFileFileName implements FileSetter {
    @Override
    public File setter(String line, File file) {
        file.setFileName(line);
        return file;
    }
}
