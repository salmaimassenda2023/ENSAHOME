package com.example.ensahome_backend.controller;

import com.example.ensahome_backend.model.Notification;
import com.example.ensahome_backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    


    // Créer une notification pour une nouvelle publication
    @PostMapping("/publication")
    public ResponseEntity<Notification> createPublicationNotification(
            @RequestParam String userId,
            @RequestParam String annonceType,
            @RequestBody Object annonce) {
        Notification notification = notificationService.createPublicationNotification(userId, annonce, annonceType);
        return ResponseEntity.ok(notification);
    }

    // Créer une notification d'expiration
    @PostMapping("/expiration")
    public ResponseEntity<Notification> createExpirationNotification(
            @RequestParam String userId,
            @RequestParam String annonceType,
            @RequestBody Object annonce) {
        Notification notification = notificationService.createExpirationNotification(userId, annonce, annonceType);
        return ResponseEntity.ok(notification);
    }

    // Récupérer les notifications d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String userId) {
        List<Notification> notifications = notificationService.getUserNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // Marquer une notification comme lue
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable String notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok().build();
    }

} 