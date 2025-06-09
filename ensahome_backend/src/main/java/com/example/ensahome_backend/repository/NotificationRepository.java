package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    
    List<Notification> findByUserIdAndIsActiveTrueAndDateExpirationAfterOrderByDateCreationDesc(
        String userId, 
        LocalDateTime now
    );
    
    void deleteByDateExpirationBefore(LocalDateTime date);
} 