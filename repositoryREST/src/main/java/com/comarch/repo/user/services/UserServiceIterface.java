package com.comarch.repo.user.services;

import com.comarch.repo.user.domain.DefaultUser;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserServiceIterface {
    DefaultUser putUser(DefaultUser defaultUser) throws NoSuchAlgorithmException, IOException;

    DefaultUser getUser(DefaultUser defaultUser) throws NoSuchAlgorithmException;

    DefaultUser getUser(String userID);

    List<DefaultUser> getUsers(DefaultUser user);

    DefaultUser upOrDegUser(String id,boolean admin) throws IOException;

    DefaultUser deleteUser(String id) throws IOException;

    DefaultUser changePassword(String id, String oldPassword, String newPassword) throws NoSuchAlgorithmException;

    DefaultUser editUser(DefaultUser defaultUser) throws IOException;

    List<DefaultUser> getAllUsers();
}
