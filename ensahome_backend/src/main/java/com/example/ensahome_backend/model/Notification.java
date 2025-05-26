package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    
    private String userId; // ID de l'utilisateur qui reçoit la notification
    private String title;
    private String message;
    private String type; // EXPIRATION, NEW_ANNOUNCEMENT
    private boolean isRead;
    private LocalDateTime createdAt;
    private String announcementId; // ID de l'annonce liée à la notification
    private boolean canRenew; // Indique si l'annonce peut être renouvelée
    private List<String> etudiantIds; // Liste des étudiants qui ont reçu cette notification

    public Notification() {
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public boolean isCanRenew() {
        return canRenew;
    }

    public void setCanRenew(boolean canRenew) {
        this.canRenew = canRenew;
    }

    public List<String> getEtudiantIds() {
        return etudiantIds;
    }

    public void setEtudiantIds(List<String> etudiantIds) {
        this.etudiantIds = etudiantIds;
    }
} 