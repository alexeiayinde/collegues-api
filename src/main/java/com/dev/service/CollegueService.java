package com.dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.entite.Collegue;
import com.dev.exception.CollegueInvalideException;
import com.dev.persistence.CollegueRepository;

@Service
public class CollegueService {

    @Autowired
    private CollegueRepository collegueRepository;

    private static final int AGE_MINIMUM = 18;
    private static final int TAILLE_EMAIL_MINIMUM = 3;
    private static final int TAILLE_PRENOM_MINIMUM = 2;
    private static final int TAILLE_NOM_MINIMUM = 2;

    public CollegueService() {
    }

    @PostConstruct
    public void init() {
        Collegue c1 = new Collegue(UUID.randomUUID().toString(), "Ayinde", "Alexei", "alexei.ayinde@dta.com", LocalDate.of(1987, 10, 01),
                "url photo");
        collegueRepository.save(c1);
    }

    public List<Collegue> listerCollegues() {
        return collegueRepository.findAll();
    }

    public List<Collegue> rechercherParNom(String nom) {
        return collegueRepository.findByNom(nom);
    }

    public Optional<Collegue> rechercherParMatricule(String matricule) {
        return collegueRepository.findById(matricule);
    }

    public Collegue creerCollegue(Collegue collegue) {
        if (collegue.getNom().trim().length() >= TAILLE_NOM_MINIMUM
                && collegue.getPrenoms().trim().length() >= TAILLE_PRENOM_MINIMUM
                && collegue.getEmail().trim().length() >= TAILLE_EMAIL_MINIMUM
                && collegue.getEmail().contains("@")
                && Period.between(collegue.getDateDeNaissance(), LocalDate.now()).getYears() >= AGE_MINIMUM) {
            collegue.setMatricule(UUID.randomUUID().toString());
            return collegueRepository.save(collegue);
        }
        throw new CollegueInvalideException("Veuillez saisir un collègue valide");
    }

    public Collegue modifierEmail(String matricule, String email) {
        Collegue c = rechercherParMatricule(matricule)
                .orElseThrow(() -> new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : email"));
        if (email.trim().length() >= TAILLE_EMAIL_MINIMUM && email.contains("@")) {
            c.setEmail(email);
        }
        return collegueRepository.save(c);
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
        Collegue c = rechercherParMatricule(matricule)
                .orElseThrow(() -> new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : photoUrl"));

        if (photoUrl.startsWith("http")) {
            c.setPhotoUrl(photoUrl);
        }
        return collegueRepository.save(c);
    }

}
