package com.example.ensahome_backend.service;

import com.example.ensahome_backend.model.Equipement;
import com.example.ensahome_backend.repository.EquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipementService {
    private final EquipementRepository equipementRepository;

    @Autowired
    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    public Optional<Equipement> findById(String id) {
        return equipementRepository.findById(id);
    }

    public Equipement save(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public void deleteById(String id) {
        equipementRepository.deleteById(id);
    }

    // Méthodes personnalisées
    public List<Equipement> findByNom(String nom) {
        return equipementRepository.findByNom(nom);
    }

    public List<Equipement> findByDescription(String description) {
        return equipementRepository.findByDescription(description);
    }

    public List<Equipement> findByPrix(double prix) {
        return equipementRepository.findByPrix(prix);
    }

    public List<Equipement> findByDisponibilite(boolean disponibilite) {
        return equipementRepository.findByDisponibilite(disponibilite);
    }

    public List<Equipement> findByNomAndDisponibilite(String nom, boolean disponibilite) {
        return equipementRepository.findByNomAndDisponibilite(nom, disponibilite);
    }

    public List<Equipement> findByPrixAndDisponibilite(double prix, boolean disponibilite) {
        return equipementRepository.findByPrixAndDisponibilite(prix, disponibilite);
    }

    public List<Equipement> findByNomAndPrix(String nom, double prix) {
        return equipementRepository.findByNomAndPrix(nom, prix);
    }
}