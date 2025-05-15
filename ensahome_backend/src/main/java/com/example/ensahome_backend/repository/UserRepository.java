package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByEmailOrUsername(String email, String username);
    Optional<User> findByEmailOrUsernameAndPassword(String email, String username, String password);
    
} 