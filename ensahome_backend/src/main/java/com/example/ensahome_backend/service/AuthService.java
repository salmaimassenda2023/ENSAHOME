package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final String SECRET_KEY = "votre_cle_secrete_tres_longue_et_complexe";
    private final long EXPIRATION_TIME = 864_000_000; // 10 jours

    public Map<String, Object> login(String emailOrUsername, String password) {
        Optional<User> userOpt = userRepository.findByEmailOrUsernameAndPassword(emailOrUsername, emailOrUsername, password);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = generateToken(user);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            return response;
        }
        return null;
    }

    public void logout(String token) {
        // Dans une implémentation plus avancée, vous pourriez invalider le token
        // en l'ajoutant à une liste noire de tokens
    }

    public User updateUser(String userId, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Mettre à jour les champs non sensibles
            user.setNom(updatedUser.getNom());
            user.setPrenom(updatedUser.getPrenom());
            // Ne pas mettre à jour l'email, le mot de passe ou le rôle ici
            return userRepository.save(user);
        }
        return null;
    }

    public boolean updatePassword(String userId, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public User validateToken(String token) {
        try {
            String userId = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            
            return userRepository.findById(userId).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}