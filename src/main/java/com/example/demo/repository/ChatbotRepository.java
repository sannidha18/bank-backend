package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ChatbotQuery;

@Repository
public interface ChatbotRepository extends JpaRepository<ChatbotQuery, Long> {

    ChatbotQuery findByQuery(String query);

}