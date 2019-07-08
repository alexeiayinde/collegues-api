package com.dev.entite;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chocolatine")
public class Collegue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String matricule;
    @Column
    private String nom;
    @Column
    private String prenoms;
    @Column
    private String email;
    @Column
    private LocalDate dateDeNaissance;
    @Column
    private String photoUrl;

    public Collegue() {
    }

    public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance,
            String photoUrl) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenoms = prenoms;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
        this.photoUrl = photoUrl;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule.trim();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.trim();
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Collegue [matricule=");
        builder.append(matricule);
        builder.append(", nom=");
        builder.append(nom);
        builder.append(", prenoms=");
        builder.append(prenoms);
        builder.append(", email=");
        builder.append(email);
        builder.append(", dateDeNaissance=");
        builder.append(dateDeNaissance);
        builder.append(", photoUrl=");
        builder.append(photoUrl);
        builder.append("]");
        return builder.toString();
    }

}
