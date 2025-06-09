package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Announcement;
import com.example.ensahome_backend.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private NotificationService notificationService;

    public Announcement createAnnouncement(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        announcement.setExpiresAt(announcement.getCreatedAt().plusDays(30));
        announcement.setActive(true);
        
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        
        // Créer des notifications pour les étudiants
        notificationService.createNewAnnouncementNotification(savedAnnouncement);
        
        return savedAnnouncement;
    }

    public Optional<Announcement> getAnnouncement(String id) {
        return announcementRepository.findById(id);
    }

    public List<Announcement> getActiveAnnouncements() {
        return announcementRepository.findByActive(true);
    }

    public List<Announcement> getUserAnnouncements(String authorId) {
        return announcementRepository.findByAuthorId(authorId);
    }

    public Announcement updateAnnouncement(String id, Announcement announcementDetails) {
        Optional<Announcement> announcementOpt = announcementRepository.findById(id);
        if (announcementOpt.isPresent()) {
            Announcement announcement = announcementOpt.get();
            announcement.setTitle(announcementDetails.getTitle());
            announcement.setActive(announcementDetails.isActive());
            return announcementRepository.save(announcement);
        }
        return null;
    }

    public void deleteAnnouncement(String id) {
        announcementRepository.deleteById(id);
    }

    public Announcement renewAnnouncement(String id) {
        Optional<Announcement> announcementOpt = announcementRepository.findById(id);
        if (announcementOpt.isPresent()) {
            Announcement announcement = announcementOpt.get();
            announcement.setActive(true);
            announcement.setCreatedAt(LocalDateTime.now());
            announcement.setExpiresAt(announcement.getCreatedAt().plusDays(30));
            return announcementRepository.save(announcement);
        }
        return null;
    }

    public List<Announcement> getExpiredAnnouncements() {
        return announcementRepository.findByExpiresAtBeforeAndActiveTrue(LocalDateTime.now());
    }
} 