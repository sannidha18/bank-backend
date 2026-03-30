package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 🔹 Register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // 🔹 Get all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔹 Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User loggedUser = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        if (loggedUser == null) {
            return ResponseEntity.status(401).body("Invalid username/password ❌");
        }

        return ResponseEntity.ok(loggedUser);
    }

    // 🔹 Get user by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found ❌");
        }

        return ResponseEntity.ok(user);
    }
}