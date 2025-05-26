package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Notification;
import com.example.ensahome_backend.model.Announcement;
import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.repository.NotificationRepository;
import com.example.ensahome_backend.repository.AnnouncementRepository;
import com.example.ensahome_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private UserRepository userRepository;

    // Méthode pour créer une notification d'expiration
    public void createExpirationNotification(Announcement announcement, User user) {
        Notification notification = new Notification();
        notification.setUserId(user.getId());
        notification.setTitle("Annonce expirée");
        notification.setMessage("Votre annonce a expiré automatiquement après 30 jours. " +
                "Les annonces sont retirées de la plateforme après un mois pour garantir la fraîcheur des logements proposés.");
        notification.setType("EXPIRATION");
        notification.setAnnouncementId(announcement.getId());
        notification.setCanRenew(user.getRole().equals("PROPRIETAIRE")); // Seuls les propriétaires peuvent renouveler
        
        notificationRepository.save(notification);
    }

    // Méthode pour créer une notification de nouvelle annonce
    public void createNewAnnouncementNotification(Announcement announcement) {
        // Récupérer tous les étudiants
        List<User> students = userRepository.findByRole("ETUDIANT");
        
        for (User student : students) {
            Notification notification = new Notification();
            notification.setUserId(student.getId());
            notification.setTitle("Nouvelle annonce disponible");
            notification.setMessage("Une nouvelle annonce a été publiée : " + announcement.getTitle());
            notification.setType("NEW_ANNOUNCEMENT");
            notification.setAnnouncementId(announcement.getId());
            notification.setCanRenew(false);
            
            notificationRepository.save(notification);
        }
    }

    // Méthode pour marquer une notification comme lue
    public void markAsRead(String notificationId) {
        Optional<Notification> notificationOpt = notificationRepository.findById(notificationId);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            notificationRepository.save(notification);
        }
    }

    // Méthode pour récupérer les notifications d'un utilisateur
    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Méthode pour récupérer les notifications non lues d'un utilisateur
    public List<Notification> getUnreadUserNotifications(String userId) {
        return notificationRepository.findByUserIdAndIsRead(userId, false);
    }

    // Méthode pour récupérer les notifications par type
    public List<Notification> getUserNotificationsByType(String userId, String type) {
        return notificationRepository.findByUserIdAndType(userId, type);
    }

    // Tâche planifiée pour vérifier les annonces expirées
    @Scheduled(cron = "0 0 0 * * ?") // Exécution tous les jours à minuit
    public void checkExpiredAnnouncements() {
        LocalDateTime now = LocalDateTime.now();
        List<Announcement> expiredAnnouncements = announcementRepository.findByExpiresAtBeforeAndActiveTrue(now);

        for (Announcement announcement : expiredAnnouncements) {
            announcement.setActive(false);
            announcementRepository.save(announcement);
            
            Optional<User> userOpt = userRepository.findById(announcement.getAuthorId());
            if (userOpt.isPresent()) {
                createExpirationNotification(announcement, userOpt.get());
            }
        }
    }
} 