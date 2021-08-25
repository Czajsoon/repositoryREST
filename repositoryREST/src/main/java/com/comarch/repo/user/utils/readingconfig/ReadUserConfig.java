package com.comarch.repo.user.utils.readingconfig;

import com.comarch.repo.user.domain.DefaultUser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReadUserConfig {

    private static final Path DIR = Paths.get("D:\\uploads\\users.txt");

    public static Map<String, DefaultUser> readUsers() throws IOException {
        Map<String, DefaultUser> usersList = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(DIR.toAbsolutePath().toString()));
        System.setProperty("true","true");
        System.setProperty("false","false");
        DefaultUserFactory defaultUserFactory = new DefaultUserFactory();
        String line;
        DefaultUser defaultUser = new DefaultUser();
        while ((line = br.readLine()) != null) {
            defaultUser = defaultUserFactory.useFactoryUser(line,defaultUser);
            boolean userflag = line.startsWith("Admin: ");
            if(userflag){
                usersList.put(defaultUser.getId(), defaultUser);
                defaultUser = new DefaultUser();
            }
        }
        return usersList;
    }
}
