package com.comarch.repo.user.utils.readingconfig;

import com.comarch.repo.user.domain.DefaultUser;

public interface DefaultUserSetter {

    DefaultUser setter(String line,DefaultUser user);

}
