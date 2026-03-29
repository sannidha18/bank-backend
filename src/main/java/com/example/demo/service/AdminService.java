package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.TransactionRepository;

import java.util.*;
import java.io.FileWriter; // ✅ NEW IMPORT

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // store chatbot queries
    private List<String> queries = new ArrayList<>();

    // 👥 USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 💸 TRANSACTIONS
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // 💬 SAVE QUERY
    public void saveQuery(String query) {
        queries.add(query);
    }

    // 💬 GET QUERIES
    public List<String> getQueries() {
        return queries;
    }

    // 📄 ADD TO CSV (🔥 FIX ADDED)
    public String addToCSV(String question, String answer) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/chatbot_datasets.csv", true);

            writer.append("\n" + question + "," + answer);

            writer.close();

            return "Added successfully ✅";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error ❌";
        }
    }
}