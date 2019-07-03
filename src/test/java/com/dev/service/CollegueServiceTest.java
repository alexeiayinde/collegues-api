package com.dev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.dev.entite.Collegue;
import com.dev.exception.CollegueInvalideException;
import com.dev.util.Constantes;

class CollegueServiceTest {

    CollegueService collegueService = Constantes.COLLEGUE_SERVICE;
    Collegue collegue;

    @Test
    void testNomCollegue() throws CollegueInvalideException {
        // test cas nombre caractères pas suffisant
        collegue = new Collegue("Test", "a", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        // test cas nombre caractères pas suffisant en raison d'espaces vides
        collegue = new Collegue("Test", "   ", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        collegue = new Collegue("Test", "aa", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertEquals(Collegue.class, collegueService.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    void testPrenomCollegue() throws CollegueInvalideException {
        // test cas nombre caractères pas suffisant
        collegue = new Collegue("Test", "ayinde", "a", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        // test cas nombre caractères pas suffisant en raison d'espaces vides
        collegue = new Collegue("Test", "ayinde", "   ", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        collegue = new Collegue("Test", "ayinde", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertEquals(Collegue.class, collegueService.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    void testEmailCollegue() throws CollegueInvalideException {
        // test cas nombre caractères pas suffisant
        collegue = new Collegue("Test", "ayinde", "alex", "@", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        // test cas nombre caractères pas suffisant en raison d'espaces vides
        collegue = new Collegue("Test", "ayinde", "alex", "     @", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        // test cas sans @
        collegue = new Collegue("Test", "ayinde", "alex", "alexatdta.com", LocalDate.of(1900, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        // test cas où email est correctement saisi
        collegue = new Collegue("Test", "ayinde", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertEquals(Collegue.class, collegueService.ajouterUnCollegue(collegue).getClass());
    }

    @Test
    void testDateDeNaissanceCollegue() throws CollegueInvalideException {
        // test cas age < 18
        collegue = new Collegue("Test", "ayinde", "alex", "alex@DTA.com", LocalDate.of(2018, 01, 01), "test");
        assertThrows(CollegueInvalideException.class, () -> collegueService.ajouterUnCollegue(collegue));

        collegue = new Collegue("Test", "ayinde", "alex", "alex@DTA.com", LocalDate.of(1900, 01, 01), "test");
        assertEquals(Collegue.class, collegueService.ajouterUnCollegue(collegue).getClass());
    }

}