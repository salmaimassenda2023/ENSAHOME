package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Equipement;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface EquipementRepository extends MongoRepository<Equipement, String> {
    List<Equipement> findByNom(String nom);
    List<Equipement> findByDescription(String description);
    List<Equipement> findByPrix(double prix);
    List<Equipement> findByDisponibilite(boolean disponibilite);
    List<Equipement> findByNomAndDisponibilite(String nom, boolean disponibilite);
    List<Equipement> findByPrixAndDisponibilite(double prix, boolean disponibilite);
    List<Equipement> findByNomAndPrix(String nom, double prix);
} 