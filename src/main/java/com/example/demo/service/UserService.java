package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 🔹 Register User
    public User registerUser(User user) {

        user.setRole("USER"); // default role
        user.setBalance(10000); // initial balance

        return userRepository.save(user);
    }

    // 🔹 Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔹 LOGIN (ADMIN + USER)
    public User login(String username, String password) {

        // 🔥 ADMIN LOGIN
        if (username != null && password != null &&
            username.trim().equals("admin") &&
            password.trim().equals("admin123")) {

            User admin = new User();
            admin.setId(999L);
            admin.setUsername("admin");
            admin.setRole("ADMIN");
            admin.setBalance(0);

            return admin;
        }

        // 👤 NORMAL USER LOGIN
        User user = userRepository.findByUsername(username);

        // ✅ SAFE CHECK (FIX)
        if (user != null && user.getPassword() != null &&
            user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    // 🔹 Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}