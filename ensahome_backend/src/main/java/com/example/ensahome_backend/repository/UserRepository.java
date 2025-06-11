package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email, String password);
    List<User> findByRole(String role);
    Optional<User> findById(String id);

} 