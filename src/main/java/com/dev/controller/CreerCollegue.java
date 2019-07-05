package com.dev.controller;

import java.time.LocalDate;

import com.dev.entite.Collegue;

public class CreerCollegue {

    private String nom;
    private String prenoms;
    private String email;
    private LocalDate dateDeNaissance;
    private String photoUrl;

    public CreerCollegue() {
    }

    public CreerCollegue(String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
    }

    public Collegue toCollegue() {
        return new Collegue(null, this.nom, this.prenoms, this.email, this.dateDeNaissance, this.photoUrl);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
