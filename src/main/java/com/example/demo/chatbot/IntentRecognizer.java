package com.example.demo.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IntentRecognizer {

    @Autowired
    private CSVLoader csvLoader;

    public String getResponse(String userQuery) {

        Map<String, String> dataset = csvLoader.loadData();
        
        System.out.println(dataset);

        userQuery = userQuery.toLowerCase();
        

        for (String key : dataset.keySet()) {

            if (userQuery.contains(key)) {
            	System.out.println(dataset);

                return dataset.get(key);
            }
        }

        return "Sorry, I could not understand your query. Please contact admin.";
    }
    
}