package com.comarch.repo.user.controllers;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.services.UserServiceIterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@Slf4j
public class UserDeleteController {

    @Autowired
    UserServiceIterface userService;

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<DefaultUser> deleteUser(@PathVariable String id) throws IOException {
        DefaultUser deletedUser = userService.deleteUser(id);
        log.info("DELETE: " + deletedUser);
        return ResponseEntity.ok().body(deletedUser);
    }
}
