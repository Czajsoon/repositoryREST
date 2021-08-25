package com.comarch.repo.user.services;
import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.repository.InMemoryRepositoryUsersInterface;
import com.comarch.repo.utils.HashPassword;
import com.comarch.repo.user.utils.Saveconfig;
import com.comarch.repo.user.utils.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceIterface {

    @Autowired
    InMemoryRepositoryUsersInterface inMemoryRepositoryUsers;

    @Override
    public DefaultUser putUser(DefaultUser defaultUser) throws NoSuchAlgorithmException, IOException {
        return inMemoryRepositoryUsers.putUser(defaultUser);
    }

    @Override
    public DefaultUser getUser(DefaultUser defaultUser) throws NoSuchAlgorithmException {
        return inMemoryRepositoryUsers.getUser(defaultUser);
    }

    @Override
    public DefaultUser getUser(String userID) {
        return inMemoryRepositoryUsers.getUser(userID);
    }

    @Override
    public List<DefaultUser> getUsers(DefaultUser user){
        List<DefaultUser> list = getAllUsers();
        list = Search.searchUsers(user,list);
        if (list.size() == 0)
            return null;
        return list;
    }

    @Override
    public DefaultUser upOrDegUser(String id,boolean admin) throws IOException {
        getUser(id).setAdmin(admin);
        Saveconfig.saveAllUsers(getAllUsers());
        return getUser(id);
    }

    @Override
    public DefaultUser deleteUser(String id) throws IOException {
        return inMemoryRepositoryUsers.deleteUser(id);
    }

    @Override
    public DefaultUser changePassword(String id, String oldPassword, String newPassword) throws NoSuchAlgorithmException {
        DefaultUser inMemDefaultUser = inMemoryRepositoryUsers.getUser(id);
        if(inMemDefaultUser.getPassword().equals(HashPassword.getPassword(oldPassword)))
            inMemDefaultUser.setPassword(HashPassword.getPassword(newPassword));
        else
            return null;
        return inMemDefaultUser;
    }

    @Override
    public DefaultUser editUser(DefaultUser defaultUser) throws IOException {
        return inMemoryRepositoryUsers.editUser(defaultUser);
    }

    @Override
    public List<DefaultUser> getAllUsers(){
        return new ArrayList<>(inMemoryRepositoryUsers.getUsers().values());
    }
}
