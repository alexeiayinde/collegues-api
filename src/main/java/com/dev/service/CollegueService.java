package com.dev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.dev.entite.Collegue;

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

}
