package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "logements")
public class Logement {
    @Id
    private String id;
    private float loyer;
    private String description;
    private List<String> photos;
    private String adresse;
    private int nombrePieces;
    private TypeLogement type;
    private int etage;
    private List<Commodite> commodites;
    private String proprietaireId; // ID du propriétaire
    private String proximite;
    private String ville;
    private boolean estDisponible;
    private String userId;
    

    public Logement() {
        this.estDisponible = true; // Par défaut, un nouveau logement est disponible
    }

    public boolean isEstDisponible() { return estDisponible; }
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }

    public String getProximite() { return proximite; }
    public void setProximite(String proximite) { this.proximite = proximite; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

   
    public String getAdresee() { return adresse; }
    public void setAdresee(String adresse) { this.adresse = adresse; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public int getEtage() { return etage; }
    public void setEtage(int etage) { this.etage = etage; }

    public int getNombrePieces() { return nombrePieces; }
    public void setNombrePieces(int nombrePieces) { this.nombrePieces = nombrePieces; }

    public float getLoyer() { return loyer; }
    public void setLoyer(float loyer) { this.loyer = loyer; }

    public TypeLogement getType() { return type; }
    public void setType(TypeLogement type) { this.type = type; }

    public List<Commodite> getCommodites() { return commodites; }
    public void setCommodites(List<Commodite> commodites) { this.commodites = commodites; }

    public String getProprietaireId() { return proprietaireId; }
    public void setProprietaireId(String proprietaireId) { this.proprietaireId = proprietaireId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
} 