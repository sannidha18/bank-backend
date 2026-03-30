package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Transaction;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Deposit Money
    @PostMapping("/deposit")
    public String deposit(@RequestBody Transaction transaction) {

        transactionService.deposit(transaction.getUserId(), transaction.getAmount());

        return "Deposit Successful";
    }

    // Withdraw Money
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Transaction transaction) {

        transactionService.withdraw(transaction.getUserId(), transaction.getAmount());

        return "Withdraw Successful";
    }

    // Transfer Money
    @PostMapping("/transfer")
    public String transfer(@RequestBody Map<String,Object> data){

        Long senderId = Long.valueOf(data.get("senderId").toString());
        Long receiverId = Long.valueOf(data.get("receiverId").toString());
        double amount = Double.parseDouble(data.get("amount").toString());
        String password = data.get("password").toString();

        return transactionService.transfer(senderId,receiverId,amount,password);
    }
    @GetMapping("/history/{userId}")
    public List<Transaction> getHistory(@PathVariable Long userId){
        return transactionService.getTransactions(userId);
    }
}