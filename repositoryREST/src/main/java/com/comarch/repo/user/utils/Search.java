package com.comarch.repo.user.utils;

import com.comarch.repo.user.domain.DefaultUser;

import java.util.List;
import java.util.stream.Collectors;

public class Search {
    private static List<DefaultUser> getUsersAdmins(boolean admin, List<DefaultUser> usersList){
        return admin
                ? usersList.stream()
                .filter(DefaultUser::isAdmin)
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> !user.isAdmin())
                .collect(Collectors.toList());
    }

    private static List<DefaultUser> getUsersLastName(DefaultUser defUser, List<DefaultUser> usersList){
        return defUser.getLastName() != null
                ? usersList
                .stream()
                .filter(user -> user.getLastName().equals(defUser.getLastName()) &&
                        defUser.isAdmin() == user.isAdmin())
                .collect(Collectors.toList())
                : getUsersAdmins(defUser.isAdmin(),usersList);
    }

    private static List<DefaultUser> getUsersFirstName(DefaultUser defUser, List<DefaultUser> usersList){
        return defUser.getFirstName() != null
                ? (defUser.getLastName() != null
                ? usersList.stream()
                .filter(user -> user.getFirstName().equals(defUser.getFirstName()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getFirstName().equals(defUser.getFirstName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList()))
                : getUsersLastName(defUser,usersList);
    }

    private static List<DefaultUser> getUsersLogin(DefaultUser defUser, List<DefaultUser> usersList){
        return defUser.getLogin() != null
                ? (defUser.getLastName() != null
                ? (defUser.getFirstName() != null
                ? usersList.stream()
                .filter(user -> user.getLogin().equals(defUser.getLogin()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getLogin().equals(defUser.getLogin()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList()))
                : (defUser.getFirstName() != null
                ? usersList.stream()
                .filter(user -> user.getLogin().equals(defUser.getLogin()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getLogin().equals(defUser.getLogin()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())))
                : getUsersFirstName(defUser, usersList);
    }

    public static List<DefaultUser> searchUsers(DefaultUser defUser, List<DefaultUser> usersList){
        return defUser.getId() != null
                ? defUser.getLogin() != null
                ? (defUser.getFirstName() != null
                ? (defUser.getLastName() != null
                ? usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getLogin().equals(defUser.getLogin()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getLogin().equals(defUser.getLogin()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList()))
                : (defUser.getLastName() != null ? usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getLogin().equals(defUser.getLogin()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getLogin().equals(defUser.getLogin()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())))
                : (defUser.getFirstName() != null
                ? (defUser.getLastName() != null
                ? usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getFirstName().equals(defUser.getFirstName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList()))
                : (defUser.getLastName() != null
                ? usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.getLastName().equals(defUser.getLastName()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())
                : usersList.stream()
                .filter(user -> user.getId().equals(defUser.getId()) &&
                        user.isAdmin() == defUser.isAdmin())
                .collect(Collectors.toList())))
                : getUsersLogin(defUser, usersList);
    }
}
