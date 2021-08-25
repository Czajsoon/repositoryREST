package com.comarch.repo.user.repository;

import com.comarch.repo.user.domain.DefaultUser;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface InMemoryRepositoryUsersInterface {
    DefaultUser putUser(DefaultUser defaultUser) throws NoSuchAlgorithmException, IOException;

    DefaultUser getUser(DefaultUser defaultUser) throws NoSuchAlgorithmException;

    DefaultUser getUser(String userID);

    DefaultUser deleteUser(String id) throws IOException;

    DefaultUser editUser(DefaultUser defaultUser) throws IOException;

    Map<String, DefaultUser> getUsers();

}
