package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Annonce;
import com.example.ensahome_backend.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class AnnonceService {
    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    public List<Annonce> findAll() {
        return annonceRepository.findAll();
    }

    public Optional<Annonce> findById(String id) {
        return annonceRepository.findById(id);
    }

    public Annonce save(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public void deleteById(String id) {
        annonceRepository.deleteById(id);
    }

    // Méthodes personnalisées
    public List<Annonce> findByUserId(String userId) {
        return annonceRepository.findByUserId(userId);
    }

    public List<Annonce> findByLogementId(String logementId) {
        return annonceRepository.findByLogementId(logementId);
    }

    public List<Annonce> findByTitre(String titre) {
        return annonceRepository.findByTitre(titre);
    }

    public List<Annonce> findByDescription(String description) {
        return annonceRepository.findByDescription(description);
    }

    public List<Annonce> findByDatePublication(Date datePublication) {
        return annonceRepository.findByDatePublication(datePublication);
    }

    public List<Annonce> findByEstActive(boolean estActive) {
        return annonceRepository.findByEstActive(estActive);
    }

    public List<Annonce> findByUserIdAndEstActive(String userId, boolean estActive) {
        return annonceRepository.findByUserIdAndEstActive(userId, estActive);
    }

    public List<Annonce> findByLogementIdAndEstActive(String logementId, boolean estActive) {
        return annonceRepository.findByLogementIdAndEstActive(logementId, estActive);
    }

    public List<Annonce> findByTitreAndEstActive(String titre, boolean estActive) {
        return annonceRepository.findByTitreAndEstActive(titre, estActive);
    }

    public List<Annonce> findByDatePublicationAndEstActive(Date datePublication, boolean estActive) {
        return annonceRepository.findByDatePublicationAndEstActive(datePublication, estActive);
    }
}