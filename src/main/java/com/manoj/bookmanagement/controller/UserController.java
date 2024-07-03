package com.manoj.bookmanagement.controller;

import com.manoj.bookmanagement.model.User;
import com.manoj.bookmanagement.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Username or password is missing.");
        }

        if (userService.isUsernameTaken(username)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
        }

        userService.registerUser(username, password);
        return ResponseEntity.ok("User registered successfully.");
    }

}
