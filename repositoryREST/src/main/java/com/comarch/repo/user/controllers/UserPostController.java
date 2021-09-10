package com.comarch.repo.user.controllers;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.services.UserServiceIterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin("*")
@RestController
@Slf4j
public class UserPostController {

    @Autowired
    UserServiceIterface userService;

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> postCreateUser(@RequestBody DefaultUser defaultUser) throws NoSuchAlgorithmException, IOException {
        System.out.println(defaultUser);
        log.info("POST: " + defaultUser);
        return ResponseEntity.ok()
                .body(userService.putUser(defaultUser) != null);
    }
}
