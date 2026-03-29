package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AiService {

    @Value("${AI_API_KEY}")
    private String apiKey;

    @SuppressWarnings("unchecked")
    public String getAIResponse(String query) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "https://api.groq.com/openai/v1/chat/completions";

            Map<String, Object> body = new HashMap<>();
            body.put("model", "llama-3.1-8b-instant");  // ✅ FIXED MODEL

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "system", "content", "You are a helpful banking assistant."));
            messages.add(Map.of("role", "user", "content", query));

            body.put("messages", messages);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request =
                    new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            Map responseBody = response.getBody();

            List<Map<String, Object>> choices =
                    (List<Map<String, Object>>) responseBody.get("choices");

            Map<String, Object> message =
                    (Map<String, Object>) choices.get(0).get("message");

            return message.get("content").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI error ❌ " + e.getMessage();
        }
    }
}