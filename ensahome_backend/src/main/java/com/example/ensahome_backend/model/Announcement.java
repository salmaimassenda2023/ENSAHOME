package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;

@Document(collection = "announcements")
public class Announcement {
    @Id
    private String id;

    @Indexed
    private String ville;
    private String title;
    private String authorId;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;//expiration date apres 30 jours
    private String logementId;
    private String equipementId;

    // Constructors
    public Announcement() {
        this.createdAt = LocalDateTime.now();
        this.expiresAt = createdAt.plusDays(30);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getLogementId() {
        return logementId;
    }

    public void setLogementId(String logementId) {
        this.logementId = logementId;
    }

    public String getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(String equipementId) {
        this.equipementId = equipementId;
    }
} 