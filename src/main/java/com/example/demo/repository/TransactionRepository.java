package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find transactions of a specific user
    List<Transaction> findByUserId(Long userId);

}

