package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.chatbot.IntentRecognizer;
import com.example.demo.entity.ChatbotQuery;
import com.example.demo.repository.ChatbotRepository;

import java.util.List;

@Service
public class ChatbotService {

    @Autowired
    private IntentRecognizer intentRecognizer;

    @Autowired
    private ChatbotRepository chatbotRepository;

    public String getResponse(String query) {

        String response = intentRecognizer.getResponse(query);

        return response;
    }

    public ChatbotQuery saveQuery(ChatbotQuery query) {

        return chatbotRepository.save(query);
    }
    
    public List<ChatbotQuery> getAllQueries() {

        return chatbotRepository.findAll();
    }
}