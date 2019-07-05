package com.dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dev.entite.Collegue;
import com.dev.exception.CollegueInvalideException;
import com.dev.exception.CollegueNonTrouveException;

public class CollegueService {

    private static final int AGE_MINIMUM = 18;
    private static final int TAILLE_EMAIL_MINIMUM = 3;
    private static final int TAILLE_PRENOM_MINIMUM = 2;
    private static final int TAILLE_NOM_MINIMUM = 2;

    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {

        Stream.of(new Collegue(UUID.randomUUID().toString(), "Ayinde", "Alexei", "alexei.ayinde@DTA.com",
                LocalDate.of(2000, 10, 1), "images/photoDefaut.jpg"),
                new Collegue(UUID.randomUUID().toString(), "Chauvin", "Adrien", "adrien.chauvin@DTA.com",
                        LocalDate.of(1999, 06, 10), "images/photoDefaut.jpg"),
                new Collegue(UUID.randomUUID().toString(), "Oddet", "Rossi", "rossi.odet@DTA.com", LocalDate.of(1998, 3, 18),
                        "images/photoDefaut.jpg"),
                new Collegue(UUID.randomUUID().toString(), "Ayinde", "Diana", "diana.ayinde@DTA.com",
                        LocalDate.of(1996, 05, 19), "images/photoDefaut.jpg"),
                new Collegue(UUID.randomUUID().toString(), "Ayinde", "Anna", "anna.ayinde@DTA.com", LocalDate.of(1956, 03, 5),
                        "images/photoDefaut.jpg"))
                .forEach(collegue -> data.put(collegue.getMatricule(), collegue));
    }

    public List<Collegue> rechercherParNom(String nomRecherche) {

        return data.values().stream()
                .filter(collegue -> nomRecherche.equals(collegue.getNom()))
                .collect(Collectors.toList());
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) {

        if (data.get(matriculeRecherche) != null) {
            return data.get(matriculeRecherche);
        }

        throw new CollegueNonTrouveException();
    }

    public Collegue ajouterUnCollegue(Collegue collegue) {

        if (collegue.getNom().trim().length() >= TAILLE_NOM_MINIMUM
                && collegue.getPrenoms().trim().length() >= TAILLE_PRENOM_MINIMUM
                && collegue.getEmail().trim().length() >= TAILLE_EMAIL_MINIMUM
                && collegue.getEmail().contains("@")
                && Period.between(collegue.getDateDeNaissance(), LocalDate.now()).getYears() >= AGE_MINIMUM) {
            collegue.setMatricule(UUID.randomUUID().toString());
            data.put(collegue.getMatricule(), collegue);
            return collegue;
        }

        throw new CollegueInvalideException("Veuillez saisir un collègue valide");
    }

    public Collegue modifierEmail(String matricule, String email) {

        Collegue collegue = rechercherParMatricule(matricule);

        if (email.trim().length() >= TAILLE_EMAIL_MINIMUM && email.contains("@")) {
            collegue.setEmail(email);
            return collegue;
        }

        throw new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : email");
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

        Collegue collegue = rechercherParMatricule(matricule);

        if (photoUrl.startsWith("http")) {
            collegue.setPhotoUrl(photoUrl);
            return collegue;
        }

        throw new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : photoUrl");
    }

}
