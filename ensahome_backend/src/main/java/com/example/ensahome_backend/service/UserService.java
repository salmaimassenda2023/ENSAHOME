package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.repository.UserRepository;
import com.example.ensahome_backend.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        // Encoder le mot de passe avant de sauvegarder
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public String login(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return jwtService.generateToken(existingUser.get().getEmail());
        }
        
        throw new BadCredentialsException("Email ou mot de passe incorrect");
    }

    public User updateUser(String userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    // Mise à jour uniquement des champs autorisés
                    if (updatedUser.getNom() != null) existingUser.setNom(updatedUser.getNom());
                    if (updatedUser.getPrenom() != null) existingUser.setPrenom(updatedUser.getPrenom());
                    if (updatedUser.getTele() != null) existingUser.setTele(updatedUser.getTele());
                    if (updatedUser.getVille() != null) existingUser.setVille(updatedUser.getVille());
                    if (updatedUser.getVilleEcole() != null) existingUser.setVilleEcole(updatedUser.getVilleEcole());
                    if (updatedUser.getCin() != null) existingUser.setCin(updatedUser.getCin());
                    
                    existingUser.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(existingUser);
                })
                .orElse(null);
    }

    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                        user.setPassword(passwordEncoder.encode(newPassword));
                        user.setUpdatedAt(LocalDateTime.now());
                        userRepository.save(user);
                        return true;
                    }
                    return false;
                })
                .orElse(false);
    }
} 