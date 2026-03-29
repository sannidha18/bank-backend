package com.example.demo.chatbot;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class CSVLoader {

    public Map<String,String> loadData(){

        Map<String,String> data = new HashMap<>();

        try{

            InputStream is = getClass().getClassLoader()
                    .getResourceAsStream("chatbot_datasets.csv");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8)
            );

            String line;

            br.readLine(); // skip header

            while((line = br.readLine()) != null){

                String[] parts = line.split(",");

                if(parts.length >= 2){

                    String query = parts[0].toLowerCase().trim();
                    String response = parts[1].trim();

                    data.put(query, response);
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }
}