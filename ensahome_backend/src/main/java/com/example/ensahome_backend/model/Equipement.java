package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "equipements")
public class Equipement {
    @Id
    private String id;
    private Boolean estDisponible;
    private List<String> photos;
    private Etat etas;
    private String designation;
    private String desc;
    private float prix;
    private String ville;

    public Equipement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<String> getPhotos() { return photos; }
    public void setPhotos(List<String> photos) { this.photos = photos; }

    public Etat getEtas() { return etas; }
    public void setEtas(Etat etas) { this.etas = etas; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public float getPrix() { return prix; }
    public void setPrix(float prix) { this.prix = prix; }

    public Boolean getEstDisponible() { return estDisponible; }
    public void setEstDisponible(Boolean estDisponible) { this.estDisponible = estDisponible; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
} 