package com.example.ensahome_backend.model;

public class Etudiant {
    private String numEtudiant;
    private NiveauEtude niveau;

    public Etudiant() {}

    public String getNumEtudiant() { return numEtudiant; }
    public void setNumEtudiant(String numEtudiant) { this.numEtudiant = numEtudiant; }

    public NiveauEtude getNiveau() { return niveau; }
    public void setNiveau(NiveauEtude niveau) { this.niveau = niveau; }
} 