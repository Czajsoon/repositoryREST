package com.comarch.repo.user.controllers;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.services.UserServiceIterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin("http://localhost:4200/")
@RestController
@Slf4j
public class UserPutController {

    @Autowired
    UserServiceIterface userService;

    @PutMapping(value = "/upOrDegUser/{id}/{admin}")
    public ResponseEntity<DefaultUser> upOrDegUser(@PathVariable String id, @PathVariable boolean admin) throws IOException {
        log.info("PUT: " + id + " " + admin);
        return ResponseEntity.ok()
                .body(userService.upOrDegUser(id,admin));
    }

    @PutMapping(value = "/editUser")
    public ResponseEntity<DefaultUser> editUser(@RequestBody DefaultUser defaultUser) throws IOException {
        log.info("PUT: " + defaultUser);
        return ResponseEntity.ok()
                .body(userService.editUser(defaultUser));
    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<DefaultUser> changePassword(@RequestParam("id") String id,
                                      @RequestParam("oldPassword") String oldPass,
                                      @RequestParam("newPassword") String newPassword) throws NoSuchAlgorithmException {
        log.info("PUT: " + id + " " + oldPass + " " + newPassword);
        return ResponseEntity.ok()
                .body(userService.changePassword(id,oldPass,newPassword));
    }
}
