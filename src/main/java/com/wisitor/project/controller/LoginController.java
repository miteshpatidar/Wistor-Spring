package com.wisitor.project.controller;

import com.wisitor.project.model.User;
import com.wisitor.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private LoginService loginservice;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        try {
            System.out.println("inside login");
            String token = loginservice.login(user); // Assuming your service returns a token
            if (token != null) {
                // Return JWT token in a map on successful login
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } else {
                // Return error message in a map if credentials are invalid
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Invalid credentials"));
            }
        } catch (Exception e) {
            // Return server error message in a map
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Server error: " + e.getMessage()));
        }
    }
}
