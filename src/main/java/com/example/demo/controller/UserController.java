package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
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
    public User login(@RequestBody User user) {

        User loggedUser = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        if (loggedUser == null) {
            return null; // frontend will handle
        }

        return loggedUser;
    }

    // 🔹 Get user by ID
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}