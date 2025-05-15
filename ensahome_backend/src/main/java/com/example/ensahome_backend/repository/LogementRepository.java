package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Logement;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LogementRepository extends MongoRepository<Logement, String> {
    List<Logement> findByProprietaireId(String proprietaireId);
    List<Logement> findByEstDisponible(boolean estDisponible);
    List<Logement> findByPrix(double prix);
    List<Logement> findByPrixAndEstDisponible(double prix, boolean estDisponible);
    

} 