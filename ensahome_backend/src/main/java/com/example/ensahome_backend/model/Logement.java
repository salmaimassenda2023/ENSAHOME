package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "logements")
public class Logement {
    @Id
    private String id;
    private boolean estDisponible;
    private List<String> photos;
    private String adresse;
    private int etage;
    private int nombrePieces;
    private float loyer;
    private TypeLogement type;
    private boolean ascenseur;
    private List<String> equipements; // IDs d'Equipement
    private List<Commodite> commodites;
    private String proprietaireId; // ID du propriétaire

    public Logement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public boolean isEstDisponible() { return estDisponible; }
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }

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

    public boolean isAscenseur() { return ascenseur; }
    public void setAscenseur(boolean ascenseur) { this.ascenseur = ascenseur; }

    public List<String> getEquipements() { return equipements; }
    public void setEquipements(List<String> equipements) { this.equipements = equipements; }

    public List<Commodite> getCommodites() { return commodites; }
    public void setCommodites(List<Commodite> commodites) { this.commodites = commodites; }

    public String getProprietaireId() { return proprietaireId; }
    public void setProprietaireId(String proprietaireId) { this.proprietaireId = proprietaireId; }
} 