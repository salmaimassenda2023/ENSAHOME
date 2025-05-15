package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "annonces")
public class Annonce {
    @Id
    private String id;
    private String titre;
    private String description;
    private Date datePublication;
    private boolean estActive;
    private String userId; // ID de l'utilisateur (auteur)
    private String logementId; // ID du logement associé

    public Annonce() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date datePublication) { this.datePublication = datePublication; }

    public boolean isEstActive() { return estActive; }
    public void setEstActive(boolean estActive) { this.estActive = estActive; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLogementId() { return logementId; }
    public void setLogementId(String logementId) { this.logementId = logementId; }
} 