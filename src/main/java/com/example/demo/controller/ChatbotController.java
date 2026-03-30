package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.ChatbotService;
import com.example.demo.service.AiService;
import com.example.demo.service.AdminService; // ✅ ADD THIS

@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @Autowired
    private AiService aiService;

    @Autowired
    private AdminService adminService; // ✅ ADD THIS

    @GetMapping("/ask")
    public String ask(@RequestParam String query) {

        // 🔥 SAVE QUERY (VERY IMPORTANT for admin dashboard)
        adminService.saveQuery(query);

        // 🔥 normalize input
        String cleanedQuery = query.trim().toLowerCase();

        // ✅ TRY CSV FIRST
        String csvResponse = chatbotService.getResponse(cleanedQuery);

        // ✅ IF CSV HAS VALID RESPONSE → RETURN
        if (csvResponse != null 
            && !csvResponse.toLowerCase().contains("not understand")
            && !csvResponse.equalsIgnoreCase("I am your banking assistant 🤖")) {

            return csvResponse;
        }

        // ✅ ELSE → USE AI
        return aiService.getAIResponse(query);
    }
}