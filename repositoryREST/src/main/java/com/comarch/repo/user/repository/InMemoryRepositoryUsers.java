package com.comarch.repo.user.repository;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.utils.Saveconfig;
import com.comarch.repo.utils.*;
import com.comarch.repo.user.utils.readingconfig.ReadUserConfig;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Repository
public class InMemoryRepositoryUsers implements InMemoryRepositoryUsersInterface {

    private Map<String, DefaultUser> usersList = new HashMap<>();

    @Override
    public DefaultUser putUser(DefaultUser defaultUser) throws NoSuchAlgorithmException, IOException {
        boolean takenLogin = false;
        for(Map.Entry<String, DefaultUser> entry : usersList.entrySet()){
            if(entry.getValue().getLogin().equals(defaultUser.getLogin()))
                takenLogin = true;
        }
        if(takenLogin)
            return null;
        defaultUser.setId(Ids.generateId());
        defaultUser.setPassword(HashPassword.getPassword(defaultUser.getPassword()));
        usersList.put(defaultUser.getId(), defaultUser);
        Saveconfig.saveAllUsers(new ArrayList<>(getUsers().values()));
        DefaultUser newDefaultUser = defaultUser;
        return newDefaultUser;
    }

    @Override
    public DefaultUser getUser(DefaultUser defaultUser) throws NoSuchAlgorithmException {
        DefaultUser foundDefaultUser = null;
        String password = HashPassword.getPassword(defaultUser.getPassword());
        for(Map.Entry<String, DefaultUser> entry : usersList.entrySet()){
            if(entry.getValue().getLogin().equals(defaultUser.getLogin()) && entry.getValue().getPassword().equals(password))
                foundDefaultUser = entry.getValue();
        }
        return foundDefaultUser;
    }

    @Override
    public DefaultUser getUser(String userID){
        return usersList.get(userID);
    }

    @Override
    public DefaultUser deleteUser(String id) throws IOException {
        DefaultUser defaultUser = usersList.remove(id);
        Saveconfig.saveAllUsers(new ArrayList<>(getUsers().values()));
        return defaultUser;
    }

    @Override
    public DefaultUser editUser(DefaultUser defaultUser) throws IOException {
        DefaultUser inMemoryDefaultUser = usersList.get(defaultUser.getId());
        boolean takenLogin = false;
        for(Map.Entry<String, DefaultUser> entry : usersList.entrySet()){
            if(entry.getValue().getLogin().equals(defaultUser.getLogin()) && !entry.getValue().getId().equals(defaultUser.getId()))
                takenLogin = true;
        }
        if(takenLogin)
            return null;
        if(!defaultUser.getLogin().equals(inMemoryDefaultUser.getLogin()))
            inMemoryDefaultUser.setLogin(defaultUser.getLogin());
        if(!defaultUser.getFirstName().equals(inMemoryDefaultUser.getFirstName()))
            inMemoryDefaultUser.setFirstName(defaultUser.getFirstName());
        if(!defaultUser.getLastName().equals(inMemoryDefaultUser.getLastName()))
            inMemoryDefaultUser.setLastName(defaultUser.getLastName());
        Saveconfig.saveAllUsers(new ArrayList<>(getUsers().values()));
        return inMemoryDefaultUser;
    }

    @Override
    public Map<String, DefaultUser> getUsers(){
        return usersList;
    }

    @PostConstruct
    public void init() throws IOException {
        usersList = ReadUserConfig.readUsers();
        System.out.println(usersList);
    }
}
