package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.entity.Transaction;
import com.example.demo.service.AdminService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 👥 USERS
    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getAllUsers();
    }

    // 💸 TRANSACTIONS
    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return adminService.getAllTransactions();
    }

    // 💬 QUERIES
    @GetMapping("/queries")
    public List<String> getQueries() {
        return adminService.getQueries();
    }

    // 📄 ADD TO CSV (🔥 YOUR QUESTION)
    @PostMapping("/add")
    public String addToCSV(@RequestBody Map<String, String> data) {
        return adminService.addToCSV(
            data.get("question"),
            data.get("answer")
        );
    }
}