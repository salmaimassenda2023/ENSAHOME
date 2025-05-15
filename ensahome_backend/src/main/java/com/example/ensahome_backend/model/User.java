package com.example.ensahome_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private String role; // ADMIN, STUDENT, TEACHER, STAFF
    private String city; // KHOURIBGA, MARRAKECH, AGADIR
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String nom;
    private String prenom;
    private String villeEcole;
    private String tele;
    private String cin;
    private List<PieceJustificative> piecesJustificatives;
    private Etudiant etudiant; // null si non étudiant
    private ProprietaireLogement proprietaireLogement; // null si non proprio

    // Constructors
    public User() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVilleEcole() {
        return villeEcole;
    }

    public void setVilleEcole(String villeEcole) {
        this.villeEcole = villeEcole;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public List<PieceJustificative> getPiecesJustificatives() {
        return piecesJustificatives;
    }

    public void setPiecesJustificatives(List<PieceJustificative> piecesJustificatives) {
        this.piecesJustificatives = piecesJustificatives;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public ProprietaireLogement getProprietaireLogement() {
        return proprietaireLogement;
    }

    public void setProprietaireLogement(ProprietaireLogement proprietaireLogement) {
        this.proprietaireLogement = proprietaireLogement;
    }
} 