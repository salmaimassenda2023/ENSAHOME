package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Logement;
import com.example.ensahome_backend.model.TypeLogement;
import com.example.ensahome_backend.model.Commodite;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LogementRepository extends MongoRepository<Logement, String> {
    List<Logement> findByProprietaireId(String proprietaireId);
    List<Logement> findByEstDisponible(boolean estDisponible);
    List<Logement> findByPrix(double prix);
    List<Logement> findByPrixAndEstDisponible(double prix, boolean estDisponible);
    
    // Méthodes de tri
    List<Logement> findAllByOrderByLoyerAsc();
    List<Logement> findAllByOrderByLoyerDesc();
    List<Logement> findByType(TypeLogement type);
    List<Logement> findByTypeAndEstDisponible(TypeLogement type, boolean estDisponible);
    List<Logement> findByTypeOrderByLoyerAsc(TypeLogement type);
    List<Logement> findByTypeOrderByLoyerDesc(TypeLogement type);

    
    // Méthodes de tri par commodité

    List<Logement> findByCommoditesContaining(Commodite commodite);
    List<Logement> findByCommoditesContainingAndEstDisponible(Commodite commodite, boolean estDisponible);
    List<Logement> findByCommoditesContainingOrderByLoyerAsc(Commodite commodite);
    List<Logement> findByCommoditesContainingOrderByLoyerDesc(Commodite commodite);
    List<Logement> findByTypeAndCommoditesContaining(TypeLogement type, Commodite commodite);
    List<Logement> findByTypeAndCommoditesContainingOrderByLoyerAsc(TypeLogement type, Commodite commodite);
    List<Logement> findByTypeAndCommoditesContainingOrderByLoyerDesc(TypeLogement type, Commodite commodite);
} 