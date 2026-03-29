package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(Long userId){
        return transactionRepository.findByUserId(userId);
    }
    
    // Deposit
    public String deposit(Long userId, double amount) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return "User not found";
        }

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        // save transaction
        Transaction t = new Transaction();
        t.setUserId(userId);
        t.setType("DEPOSIT");
        t.setAmount(amount);

        transactionRepository.save(t);

        return "Deposit successful";
    }

    // Withdraw
    public String withdraw(Long userId, double amount) {

        User user = userRepository.findById(userId).orElse(null);

        if(user == null){
            return "User not found";
        }

        if(user.getBalance() < amount){
            return "Insufficient balance";
        }

        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        // save transaction
        Transaction t = new Transaction();
        t.setUserId(userId);
        t.setType("WITHDRAW");
        t.setAmount(amount);

        transactionRepository.save(t);

        return "Withdraw successful";
    }

    // Transfer (FINAL ONE ONLY)
    public String transfer(Long senderId, Long receiverId, double amount, String password){

        User sender = userRepository.findById(senderId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if(sender == null || receiver == null){
            return "User not found";
        }

        if(!sender.getPassword().equals(password)){
            return "Incorrect password";
        }

        if(sender.getBalance() < amount){
            return "Insufficient balance";
        }

        // update balances
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        // 🔥 CUSTOM TEXT
        Transaction t1 = new Transaction();
        t1.setUserId(senderId);
        t1.setType("Sent to " + receiver.getUsername());
        t1.setAmount(amount);
        t1.setTimestamp(java.time.LocalDateTime.now());

        Transaction t2 = new Transaction();
        t2.setUserId(receiverId);
        t2.setType("Received from " + sender.getUsername());
        t2.setAmount(amount);
        t2.setTimestamp(java.time.LocalDateTime.now());

        transactionRepository.save(t1);
        transactionRepository.save(t2);

        return "Transfer successful";
    }
}