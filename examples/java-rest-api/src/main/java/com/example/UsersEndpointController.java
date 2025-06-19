package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsersEndpointController {

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        // Create a list to hold the user objects
        List<Map<String, Object>> users = new ArrayList<>();

        // Create a map for each user and add them to the list
        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 1);
        user1.put("name", "bruce wayne");

        Map<String, Object> user2 = new HashMap<>();
        user2.put("id", 2);
        user2.put("name", "clark kent");

        Map<String, Object> user3 = new HashMap<>();
        user3.put("id", 3);
        user3.put("name", "diana prince");

        // Add users to the list
        users.add(user1);
        users.add(user2);
        users.add(user3);

        return users;
    }
}