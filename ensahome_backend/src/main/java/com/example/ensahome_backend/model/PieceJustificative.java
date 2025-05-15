package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class PieceJustificative {
    @Id
    private String id;
    private EnumTypeDoc type;
    private String chemin;
    private Date dateUpload;

    public PieceJustificative() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public EnumTypeDoc getType() { return type; }
    public void setType(EnumTypeDoc type) { this.type = type; }

    public String getChemin() { return chemin; }
    public void setChemin(String chemin) { this.chemin = chemin; }

    public Date getDateUpload() { return dateUpload; }
    public void setDateUpload(Date dateUpload) { this.dateUpload = dateUpload; }
} 