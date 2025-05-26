package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserId(String userId);
    List<Notification> findByUserIdAndIsRead(String userId, boolean isRead);
    List<Notification> findByAnnouncementId(String announcementId);
    List<Notification> findByUserIdAndType(String userId, String type);
} 