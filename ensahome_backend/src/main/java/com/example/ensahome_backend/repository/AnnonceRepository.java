package com.example.ensahome_backend.repository;

import com.example.ensahome_backend.model.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Date;

public interface AnnonceRepository extends MongoRepository<Annonce, String> {
    List<Annonce> findByUserId(String userId);
    List<Annonce> findByLogementId(String logementId);
    List<Annonce> findByTitre(String titre);
    List<Annonce> findByDescription(String description);
    List<Annonce> findByDatePublication(Date datePublication);
    List<Annonce> findByEstActive(boolean estActive);
    List<Annonce> findByUserIdAndEstActive(String userId, boolean estActive);
    List<Annonce> findByLogementIdAndEstActive(String logementId, boolean estActive);
    List<Annonce> findByTitreAndEstActive(String titre, boolean estActive);
    List<Annonce> findByDatePublicationAndEstActive(Date datePublication, boolean estActive);
} 