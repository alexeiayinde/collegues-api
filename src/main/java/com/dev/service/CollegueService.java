package com.dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.dev.entite.Collegue;
import com.dev.exception.CollegueInvalideException;
import com.dev.exception.CollegueNonTrouveException;

public class CollegueService {

    private Map<String, Collegue> data = new HashMap<>();

    public CollegueService() {
        String matricule = UUID.randomUUID().toString();
        data.put(matricule, new Collegue(matricule, "Ayinde", "Alexei", "alexei.ayinde@DTA.com",
                LocalDate.of(2000, 10, 1), "images/photoDefaut.jpg"));
        matricule = UUID.randomUUID().toString();
        data.put(matricule, new Collegue(matricule, "Chauvin", "Adrien", "adrien.chauvin@DTA.com",
                LocalDate.of(1999, 06, 10), "images/photoDefaut.jpg"));
        matricule = UUID.randomUUID().toString();
        data.put(matricule, new Collegue(matricule, "Oddet", "Rossi", "rossi.odet@DTA.com", LocalDate.of(1998, 3, 18),
                "images/photoDefaut.jpg"));
        matricule = UUID.randomUUID().toString();
        data.put(matricule, new Collegue(matricule, "Ayinde", "Diana", "diana.ayinde@DTA.com",
                LocalDate.of(1996, 05, 19), "images/photoDefaut.jpg"));
        matricule = UUID.randomUUID().toString();
        data.put(matricule, new Collegue(matricule, "Ayinde", "Anna", "anna.ayinde@DTA.com", LocalDate.of(1956, 03, 5),
                "images/photoDefaut.jpg"));
    }

    public List<Collegue> rechercherParNom(String nomRecherche) {
        List<Collegue> listeCollegues = new ArrayList<>();

        for (Entry<String, Collegue> element : data.entrySet()) {
            if (nomRecherche.equals(element.getValue().getNom()))
                listeCollegues.add(element.getValue());
        }
        return listeCollegues;
    }

    public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {

        if (data.get(matriculeRecherche) != null)
            return data.get(matriculeRecherche);

        throw new CollegueNonTrouveException();
    }

    public Collegue ajouterUnCollegue(Collegue collegue) throws CollegueInvalideException {
        if ((collegue.getNom().trim().length() >= 2) && (collegue.getPrenoms().trim().length() >= 2)
                && (collegue.getEmail().trim().length() >= 3) && (collegue.getEmail().contains("@"))
                && (Period.between(collegue.getDateDeNaissance(), LocalDate.now()).getYears() >= 18)) {
            collegue.setMatricule(UUID.randomUUID().toString());
            data.put(collegue.getMatricule(), collegue);
            return collegue;
        }

        throw new CollegueInvalideException();
    }

}
