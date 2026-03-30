package com.example.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> login(@RequestBody User user) {

        try {
            User loggedUser = userService.login(
                    user.getUsername(),
                    user.getPassword()
            );

            if (loggedUser == null) {
                return ResponseEntity
                        .status(401)
                        .body("Invalid username or password ❌");
            }

            return ResponseEntity.ok(loggedUser);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body("Server error ❌");
        }
    }

    // 🔹 Get user by ID
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}