package com.comarch.repo.user.utils;

import com.comarch.repo.user.domain.DefaultUser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Saveconfig {
    private static final Path directory = Paths.get("D:\\uploads\\users.txt");

    public static void saveAllUsers(List<DefaultUser> usersList) throws IOException {
        Files.deleteIfExists(directory);
        Files.createFile(directory);
        for(DefaultUser users : usersList){
            Files.writeString(directory,"ID:        " + users.getId() + "\n", StandardOpenOption.APPEND);
            Files.writeString(directory,"FirstName: " + users.getFirstName() + "\n", StandardOpenOption.APPEND);
            Files.writeString(directory,"LastName:  " +users.getLastName() + "\n", StandardOpenOption.APPEND);
            Files.writeString(directory,"Login:     " + users.getLogin() + "\n", StandardOpenOption.APPEND);
            Files.writeString(directory,"Password:  " + users.getPassword() + "\n", StandardOpenOption.APPEND);
            Files.writeString(directory,"Admin:     " + users.isAdmin() + "\n", StandardOpenOption.APPEND);
        }
    }
}
