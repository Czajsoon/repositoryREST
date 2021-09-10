package com.comarch.repo.user.controllers;

import com.comarch.repo.user.domain.DefaultUser;
import com.comarch.repo.user.services.UserServiceIterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin("*")
@RestController
@Slf4j
public class UserGetController {

    @Autowired
    UserServiceIterface userService;

    @PostMapping(value = "/signIn")
    public ResponseEntity<DefaultUser> login(@RequestBody DefaultUser defaultUser) throws NoSuchAlgorithmException {
        DefaultUser users = userService.getUser(defaultUser);
        log.info("GET: " + users);
        return ResponseEntity.ok()
                .body(users);
    }

    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<DefaultUser>> getAllUsers(){
        List<DefaultUser> users = userService.getAllUsers();
        log.info("GET: " + users);
        return ResponseEntity.ok()
                .body(users);
    }

    @GetMapping(value = "/searchUsers")
    public ResponseEntity<List<DefaultUser>> getUsers(@RequestParam(value = "id", required = false) String id,
                                      @RequestParam(value = "login",required = false) String login,
                                      @RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "admin",required = false) boolean admin){
        List<DefaultUser> users = userService.getUsers(DefaultUser.builder()
                                                                    .id(id)
                                                                    .login(login)
                                                                    .firstName(firstName)
                                                                    .lastName(lastName)
                                                                    .admin(admin)
                                                                    .build());
        log.info("GET: " + users);
        return ResponseEntity.ok().body(users);
    }
}
