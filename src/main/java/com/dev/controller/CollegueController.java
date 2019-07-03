package com.dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entite.Collegue;
import com.dev.exception.CollegueNonTrouveException;
import com.dev.service.CollegueService;
import com.dev.util.Constantes;

@RestController

@RequestMapping(path = "/collegues")

public class CollegueController {

    private CollegueService collegueService = Constantes.COLLEGUE_SERVICE;

    @RequestMapping(method = RequestMethod.GET)
    public List<String> rechercherCollegues(@RequestParam String nom) {
        List<String> listeMatricules = new ArrayList<>();

        listeMatricules = collegueService.rechercherParNom(nom).stream().map(collegue -> collegue.getMatricule())
                .collect(Collectors.toList());

        return listeMatricules;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{matricule}")
    public Collegue rechercherCollegue(@PathVariable String matricule) throws CollegueNonTrouveException {

        return collegueService.rechercherParMatricule(matricule);
    }

}
