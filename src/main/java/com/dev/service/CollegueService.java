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
        data.put("Ayinde", new Collegue(UUID.randomUUID().toString(), "Ayinde", "Alexei", "alexei.ayinde@DTA.com",
                LocalDate.of(2000, 10, 1), "images/photoDefaut.jpg"));
        data.put("Chauvin", new Collegue(UUID.randomUUID().toString(), "Chauvin", "Adrien", "adrien.chauvin@DTA.com",
                LocalDate.of(1999, 06, 10), "images/photoDefaut.jpg"));
        data.put("Odet", new Collegue(UUID.randomUUID().toString(), "Odet", "Rossi", "rossi.odet@DTA.com",
                LocalDate.of(1998, 3, 18), "images/photoDefaut.jpg"));
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
