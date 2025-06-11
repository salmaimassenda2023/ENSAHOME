package com.example.ensahome_backend.service;

import com.example.ensahome_backend.dto.PublicationDto;
import com.example.ensahome_backend.model.Announcement;
import com.example.ensahome_backend.model.Equipement;
import com.example.ensahome_backend.model.Logement;
import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.repository.AnnouncementRepository;
import com.example.ensahome_backend.repository.EquipementRepository;
import com.example.ensahome_backend.repository.LogementRepository;
import com.example.ensahome_backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    
    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private LogementRepository logementRepository;

    @Autowired
    private UserRepository userRepository;

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

    public List<PublicationDto> getCityPublications(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty() || optionalUser.get().getVille() == null) {
            // Soit l'utilisateur n'existe pas, soit la ville n'est pas définie
            return new ArrayList<>(); // ou retournez une erreur personnalisée
        }

        String ville = optionalUser.get().getVille();

        List<Announcement> announcements = announcementRepository.findByVille(ville);
        List<PublicationDto> publications = new ArrayList<>();

        for (Announcement announcement : announcements) {
            PublicationDto dto = new PublicationDto();
            dto.setId(announcement.getId());

            if (announcement.getEquipementId() != null) {
        
                Optional<Equipement> eqOpt = equipementRepository.findById(announcement.getEquipementId());
                eqOpt.ifPresent(equipement -> {
                    dto.setTypePub("equipements");
                    dto.setPhotos(equipement.getPhotos());
                    dto.setDesignation(equipement.getNom());
                    dto.setDesc(equipement.getDescription());
                    dto.setPrix(equipement.getPrix());
                    // Tu peux ajouter état et icône dans un champ Map si tu veux
                });
            } else if (announcement.getLogementId() != null) {
                Optional<Logement> logOpt = logementRepository.findById(announcement.getLogementId());
                logOpt.ifPresent(logement -> {
                    dto.setTypePub("logements");
                    dto.setPhotos(logement.getPhotos());
                    dto.setAdresee(logement.getAdresse());
                    dto.setProximite(logement.getProximite());
                    dto.setDesc(logement.getDescription());
                    dto.setLoyer(logement.getLoyer());
                    dto.setType(logement.getType().toString());
                    dto.setNombrePieces(logement.getNombrePieces());

                    List<Map<String, String>> mappedCommodites = logement.getCommodites().stream()
                        .map(commodite -> Map.of("nom", commodite.name()))
                        .collect(Collectors.toList());

                    dto.setCommodites(mappedCommodites);
                System.out.println(dto);

                });
            }

            publications.add(dto);
        }

        return publications;

    }

    public List<PublicationDto> getUserPublications(String userId) {
        List<Announcement> announcements = announcementRepository.findByAuthorId(userId);
        List<PublicationDto> publications = new ArrayList<>();

        for (Announcement announcement : announcements) {
            PublicationDto dto = new PublicationDto();
            dto.setId(announcement.getId());

            if (announcement.getEquipementId() != null) {
        
                Optional<Equipement> eqOpt = equipementRepository.findById(announcement.getEquipementId());
                eqOpt.ifPresent(equipement -> {
                    dto.setTypePub("equipements");
                    dto.setPhotos(equipement.getPhotos());
                    dto.setDesignation(equipement.getNom());
                    dto.setDesc(equipement.getDescription());
                    dto.setPrix(equipement.getPrix());
                    // Tu peux ajouter état et icône dans un champ Map si tu veux
                });
            } else if (announcement.getLogementId() != null) {
                Optional<Logement> logOpt = logementRepository.findById(announcement.getLogementId());
                logOpt.ifPresent(logement -> {
                    dto.setTypePub("logements");
                    dto.setPhotos(logement.getPhotos());
                    dto.setAdresee(logement.getAdresse());
                    dto.setProximite(logement.getProximite());
                    dto.setDesc(logement.getDescription());
                    dto.setLoyer(logement.getLoyer());
                    dto.setType(logement.getType().toString());
                    dto.setNombrePieces(logement.getNombrePieces());

                    List<Map<String, String>> mappedCommodites = logement.getCommodites().stream()
                        .map(commodite -> Map.of("nom", commodite.name()))
                        .collect(Collectors.toList());

                    dto.setCommodites(mappedCommodites);
                System.out.println(dto);

                });
            }

            publications.add(dto);
        }

        return publications;
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