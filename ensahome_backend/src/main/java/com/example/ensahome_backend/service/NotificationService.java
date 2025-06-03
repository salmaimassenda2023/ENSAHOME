package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Notification;
import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.model.Announcement;
import com.example.ensahome_backend.model.Logement;
import com.example.ensahome_backend.model.Equipement;
import com.example.ensahome_backend.repository.NotificationRepository;
import com.example.ensahome_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private static final int NOTIFICATION_VALIDITY_DAYS = 10;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public static class NotificationMessages {
        public static final String PUBLICATION_SUCCESS_MESSAGE = "Votre annonce a été publiée avec succès.";
        public static final String PUBLICATION_SUCCESS_DETAIL = "Votre annonce est maintenant visible sur la plateforme pour une durée de 30 jours.";
        
        public static final String EXPIRATION_AUTO_MESSAGE = "Votre annonce a expiré automatiquement après 30 jours.";
        public static final String EXPIRATION_AUTO_DETAIL = "Les annonces sont retirées de la plateforme après un mois pour garantir la fraîcheur des logements proposés.";
        
        public static final String REPUBLICATION_SUCCESS_MESSAGE = "Votre annonce a été republiée avec succès.";
        public static final String REPUBLICATION_SUCCESS_DETAIL = "Votre annonce est à nouveau visible sur la plateforme pour une durée de 30 jours.";
    }

    public Notification createPublicationNotification(String userId, Object annonce, String annonceType) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        boolean isProprietaire = "proprietaire".equals(user.getRole());

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(NotificationMessages.PUBLICATION_SUCCESS_MESSAGE);
        notification.setDetail(NotificationMessages.PUBLICATION_SUCCESS_DETAIL);
        notification.setDateExpiration(LocalDateTime.now().plusDays(NOTIFICATION_VALIDITY_DAYS));
        notification.setRepubliable(isProprietaire);

        if ("logement".equals(annonceType)) {
            notification.setLogement((Logement) annonce);
        } else if ("equipement".equals(annonceType)) {
            notification.setEquipement((Equipement) annonce);
        }

        return notificationRepository.save(notification);
    }

    public Notification createExpirationNotification(String userId, Object annonce, String annonceType) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        boolean isProprietaire = "proprietaire".equals(user.getRole());

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(NotificationMessages.EXPIRATION_AUTO_MESSAGE);
        notification.setDetail(NotificationMessages.EXPIRATION_AUTO_DETAIL);
        notification.setDateExpiration(LocalDateTime.now().plusDays(NOTIFICATION_VALIDITY_DAYS));
        notification.setRepubliable(isProprietaire);

        if ("logement".equals(annonceType)) {
            notification.setLogement((Logement) annonce);
        } else if ("equipement".equals(annonceType)) {
            notification.setEquipement((Equipement) annonce);
        }

        return notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(String userId) {
        return notificationRepository.findByUserIdAndIsActiveTrueAndDateExpirationAfterOrderByDateCreationDesc(
            userId, 
            LocalDateTime.now()
        );
    }

    public void markAsRead(String notificationId) {
        notificationRepository.findById(notificationId)
            .ifPresent(notification -> {
                notification.setActive(false);
                notificationRepository.save(notification);
            });
    }

    public Notification createRepublicationNotification(String userId, Object annonce, String annonceType) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(NotificationMessages.REPUBLICATION_SUCCESS_MESSAGE);
        notification.setDetail(NotificationMessages.REPUBLICATION_SUCCESS_DETAIL);
        notification.setDateExpiration(LocalDateTime.now().plusDays(NOTIFICATION_VALIDITY_DAYS));
        notification.setRepubliable(true);

        if ("logement".equals(annonceType)) {
            notification.setLogement((Logement) annonce);
        } else if ("equipement".equals(annonceType)) {
            notification.setEquipement((Equipement) annonce);
        }

        return notificationRepository.save(notification);
    }

    public void deleteExpiredNotifications() {
        notificationRepository.deleteByDateExpirationBefore(LocalDateTime.now());
    }

    public void createNewAnnouncementNotification(Announcement announcement) {
        Notification notification = new Notification();
        notification.setUserId(announcement.getAuthorId());
        notification.setMessage(NotificationMessages.PUBLICATION_SUCCESS_MESSAGE);
        notification.setDetail(NotificationMessages.PUBLICATION_SUCCESS_DETAIL);
        notification.setDateExpiration(LocalDateTime.now().plusDays(NOTIFICATION_VALIDITY_DAYS));
        notification.setRepubliable(true);
        notificationRepository.save(notification);
    }
} 