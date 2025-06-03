package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "equipements")
public class Equipement {
    @Id
    private String id;
    private boolean disponibilite;
    private List<String> photos;
    private Etat etat;
    private String nom;
    private String description;
    private double prix;
    private String ville;

    public Equipement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public Etat getEtat() { return etat; }
    public void setEtat(Etat etat) { this.etat = etat; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
} 