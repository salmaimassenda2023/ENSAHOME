package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    @DBRef
    private Logement logement;

    @DBRef
    private Equipement equipement;

    private String message;
    private String detail;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateExpiration;
    private boolean isRepubliable;
    private String userId;
    private boolean isActive = true;
    private String ville;
    private String announcementId;
    private boolean isExpirationWarning = false;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Logement getLogement() {
        return logement;
    }

    public void setLogement(Logement logement) {
        this.logement = logement;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public boolean isRepubliable() {
        return isRepubliable;
    }

    public void setRepubliable(boolean republiable) {
        isRepubliable = republiable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public boolean isExpirationWarning() {
        return isExpirationWarning;
    }

    public void setExpirationWarning(boolean expirationWarning) {
        isExpirationWarning = expirationWarning;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(dateExpiration);
    }

    public boolean isNearExpiration() {
        LocalDateTime warningDate = dateExpiration.minusDays(5);
        return LocalDateTime.now().isAfter(warningDate) && !isExpired();
    }
} 