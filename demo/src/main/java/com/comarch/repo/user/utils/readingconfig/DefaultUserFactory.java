package com.comarch.repo.user.utils.readingconfig;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.utils.readingconfig.startegy.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DefaultUserFactory {
//    @Value("11")
//    @Value(value = "${prefixWhitespaces:11}")
    public int prefixWhitespaces;
    static Map<String, DefaultUserSetter> operationsMap = new HashMap<>();
    static {
        operationsMap.put("ID:        ",new SettingDefaultUserID());
        operationsMap.put("FirstName: ",new SettingDefaultUserFirstname());
        operationsMap.put("LastName:  ",new SettingDefaultUserLastname());
        operationsMap.put("Login:     ",new SettingDefaultUserLogin());
        operationsMap.put("Password:  ",new SettingDefaultUserPassword());
        operationsMap.put("Admin:     ",new SettingDefaultUserAdmin());
    }

    public static Optional<DefaultUserSetter> operation(String opertion){
        return Optional.ofNullable(operationsMap.get(opertion));
    }

    public DefaultUser useFactoryUser(String line,DefaultUser user){
        DefaultUserSetter defaultUserSetter = DefaultUserFactory.operation(line.substring(0,11))
                .orElseThrow(() -> new IllegalArgumentException("Invalid argument!"));
        return defaultUserSetter.setter(line.substring(11),user);
    }


}
