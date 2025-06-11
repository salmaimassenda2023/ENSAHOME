package com.example.ensahome_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ensahome_backend.dto.PublicationDto;
import com.example.ensahome_backend.model.Annonce;
import com.example.ensahome_backend.model.Announcement;
import com.example.ensahome_backend.model.Equipement;
import com.example.ensahome_backend.model.Logement;
import com.example.ensahome_backend.model.Notification;
import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.security.CustomUserDetails;
import com.example.ensahome_backend.service.AnnonceService;
import com.example.ensahome_backend.service.AnnouncementService;
import com.example.ensahome_backend.service.EquipementService;
import com.example.ensahome_backend.service.LogementService;

@RestController
public class AnnouncementController {
    
    
    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private LogementService logementService;
    @Autowired
    private EquipementService equipementService;

    
    // Récupérer tous publications
    @GetMapping("/publications")
    public ResponseEntity<List<PublicationDto>> getPublications() {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = ((CustomUserDetails) userDetails).getId();

        // Récupérer les publications
        List<PublicationDto> publicationDto = announcementService.getUserPublications(userId);
        return ResponseEntity.ok(publicationDto);
    }

    // Récupérer les publications par ville
    @GetMapping("/publications/ville")
    public ResponseEntity<List<PublicationDto>> getPublicationsCity() {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = ((CustomUserDetails) userDetails).getId();

        // Récupérer les publications
        List<PublicationDto> publicationDto = announcementService.getCityPublications(userId);
        return ResponseEntity.ok(publicationDto);
    }

    // Récupérer tous les annoces
    @GetMapping("/announcements")
    public ResponseEntity<List<Announcement>> getAnnouncement() {
        List<Announcement> announcements = announcementService.getActiveAnnouncements();
        return ResponseEntity.ok(announcements);
    }

    // Récupérer les annoces d'un utilisateur
    @GetMapping("/announcements/{userId}")
    public ResponseEntity<List<Announcement>> getUserAnnouncement(@PathVariable String userId) {
        List<Announcement> announcements = announcementService.getUserAnnouncements(userId);
        return ResponseEntity.ok(announcements);
    }

    @PostMapping("/announcement/logement")
    public ResponseEntity<?> addLogement(@RequestBody Logement logement) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = ((CustomUserDetails) userDetails).getId();

        // Sauvegarder le logement
        logementService.save(logement);

        // Créer une nouvelle annonce liée au logement
        Announcement announcement = new Announcement();
        announcement.setTitle("Annonce pour logement");
        announcement.setLogementId(logement.getId());   
        announcement.setVille(logement.getVille());   
        announcement.setAuthorId(userId); // On récupére l'id à partir du JWT

        // Enregistrer l'annonce
        Announcement savedAnnouncement = announcementService.createAnnouncement(announcement);

        // Retourner l'objet avec logement et annonce
        Map<String, Object> response = new HashMap<>();
        response.put("logement", logement);
        response.put("announcement", savedAnnouncement);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/announcement/equipement")
    public ResponseEntity<?> addEquipement(@RequestBody Equipement equipement) {
        // Récupérer l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userId = ((CustomUserDetails) userDetails).getId();

        // Sauvegarder l'équipement
        equipementService.save(equipement);

        // Créer une nouvelle annonce liée au équipement
        Announcement announcement = new Announcement();
        announcement.setTitle("Annonce pour équipement");
        announcement.setEquipementId(equipement.getId());
        announcement.setVille(equipement.getVille());   
        announcement.setAuthorId(userId); // On récupére l'id à partir du JWT

        // Enregistrer l'annonce
        Announcement savedAnnouncement = announcementService.createAnnouncement(announcement);

        // Retourner l'objet avec équipement et annonce
        Map<String, Object> response = new HashMap<>();
        response.put("equipement", equipement);
        response.put("announcement", savedAnnouncement);

        return ResponseEntity.ok(response);

    }


}
