package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Announcement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
    List<Announcement> findByAuthorId(String authorId);
    List<Announcement> findByActive(boolean active);
    List<Announcement> findByExpiresAtBeforeAndActiveTrue(LocalDateTime date);
    List<Announcement> findByLogementId(String logementId);
    List<Announcement> findByEquipementId(String equipementId);
} 