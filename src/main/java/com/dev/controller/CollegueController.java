package com.dev.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entite.Collegue;
import com.dev.service.CollegueService;

@RestController

@RequestMapping(path = "/collegues")
public class CollegueController {

    @Autowired
    private CollegueService collegueService;

    @RequestMapping(method = RequestMethod.GET, path = "/lister")
    public List<Collegue> listerCollegues() {
        return collegueService.listerCollegues();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<String> rechercherCollegues(@RequestParam String nom) {

        return collegueService.rechercherParNom(nom)
                .stream()
                .map(Collegue::getMatricule)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{matricule}")
    public Optional<Collegue> rechercherCollegueParMatricule(@PathVariable String matricule) {

        return collegueService.rechercherParMatricule(matricule);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Collegue creerCollegue(@RequestBody Collegue collegue) {

        return collegueService.creerCollegue(collegue);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/{matricule}")
    public Collegue modifierCollegue(@PathVariable String matricule, @RequestBody Collegue collegue) {

        if (collegue.getEmail() != null && !collegue.getEmail().isEmpty())
            return collegueService.modifierEmail(matricule, collegue.getEmail());

        if (collegue.getPhotoUrl() != null && !collegue.getPhotoUrl().isEmpty())
            return collegueService.modifierPhotoUrl(matricule, collegue.getPhotoUrl());

        return null;
    }

}
