package com.dev.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.service.CollegueService;

@RestController

@RequestMapping(path = "/collegues")

public class CollegueController {

    private CollegueService collegueService = new CollegueService();

    @RequestMapping(method = RequestMethod.GET)
    public List<String> rechercherCollegues(@RequestParam String nom) {
        List<String> listeMatricules = new ArrayList<>();

        listeMatricules = collegueService.rechercherParNom(nom).stream().map(collegue -> collegue.getMatricule())
                .collect(Collectors.toList());

        return listeMatricules;
    }

}
