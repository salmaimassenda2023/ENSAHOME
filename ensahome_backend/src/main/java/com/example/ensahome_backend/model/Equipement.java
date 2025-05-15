package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import java.util.List;

public class Equipement {
    @Id
    private String id;
    private boolean estDisponible;
    private List<String> photos;
    private String designation;
    private String description;
    private String etat;
    private float prix;

    public Equipement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public boolean isEstDisponible() { return estDisponible; }
    public void setEstDisponible(boolean estDisponible) { this.estDisponible = estDisponible; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }
} 