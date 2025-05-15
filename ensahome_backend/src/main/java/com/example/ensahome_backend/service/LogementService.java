package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Logement;
import com.example.ensahome_backend.repository.LogementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogementService {
    private final LogementRepository logementRepository;

    @Autowired
    public LogementService(LogementRepository logementRepository) {
        this.logementRepository = logementRepository;
    }

    public List<Logement> findAll() {
        return logementRepository.findAll();
    }

    public Optional<Logement> findById(String id) {
        return logementRepository.findById(id);
    }

    public Logement save(Logement logement) {
        return logementRepository.save(logement);
    }

    public void deleteById(String id) {
        logementRepository.deleteById(id);
    }

    // Méthodes personnalisées
    public List<Logement> findByProprietaireId(String proprietaireId) {
        return logementRepository.findByProprietaireId(proprietaireId);
    }

    public List<Logement> findByEstDisponible(boolean estDisponible) {
        return logementRepository.findByEstDisponible(estDisponible);
    }
}