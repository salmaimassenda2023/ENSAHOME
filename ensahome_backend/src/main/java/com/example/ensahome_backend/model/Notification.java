package com.example.ensahome_backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Data
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

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(dateExpiration);
    }
} 