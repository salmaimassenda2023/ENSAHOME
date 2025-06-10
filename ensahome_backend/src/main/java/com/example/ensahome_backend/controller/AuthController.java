package com.example.ensahome_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            String token = service.login(user);
            return ResponseEntity.ok().body(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email ou mot de passe incorrect"));
        }
    }

    @PutMapping("/user/profile")
    public ResponseEntity<?> updateProfile(@RequestBody User updatedUser) {
        try {
            User user = service.updateUser(updatedUser.getId(), updatedUser);
            if (user != null) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Erreur lors de la mise à jour du profil: " + e.getMessage()));
        }
    }

    @PutMapping("/user/password")
    public ResponseEntity<?> updatePassword(@RequestBody Map<String, String> passwords) {
        try {
            String userId = passwords.get("userId");
            String oldPassword = passwords.get("oldPassword");
            String newPassword = passwords.get("newPassword");

            if (service.updatePassword(userId, oldPassword, newPassword)) {
                return ResponseEntity.ok().body(Map.of("message", "Mot de passe mis à jour avec succès"));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Ancien mot de passe incorrect"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Erreur lors de la mise à jour du mot de passe: " + e.getMessage()));
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users")
    public List<User> user() {
        return service.findAll();
    }

}