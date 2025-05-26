package com.example.ensahome_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.service.UserService;

@RestController
// @RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService service;


    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.save(user);
    }

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    try {
        String token = service.verify(user);
        return ResponseEntity.ok().body(Map.of("token", token));
    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Email ou mot de passe incorrect"));
    }
}

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}