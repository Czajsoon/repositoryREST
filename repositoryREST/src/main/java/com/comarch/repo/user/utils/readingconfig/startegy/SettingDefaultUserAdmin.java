package com.comarch.repo.user.utils.readingconfig.startegy;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.utils.readingconfig.DefaultUserSetter;

public class SettingDefaultUserAdmin implements DefaultUserSetter {

    @Override
    public DefaultUser setter(String line, DefaultUser user) {
        user.setAdmin(line.equals("true"));
        return user;
    }
}
