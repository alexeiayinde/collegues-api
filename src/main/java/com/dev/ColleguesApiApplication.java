package com.dev;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.dev.entite.Collegue;
import com.dev.service.CollegueService;

@SpringBootApplication
public class ColleguesApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ColleguesApiApplication.class, args);

        CollegueService collegueService = context.getBean(CollegueService.class);

        collegueService.creerCollegue(
                new Collegue(UUID.randomUUID().toString(), "Ayinde", "Diana", "diana.ayinde@dta.com", LocalDate.of(1984, 05, 19), "test"));

        collegueService.creerCollegue(
                new Collegue(UUID.randomUUID().toString(), "Adrien", "Chauvin", "adrien.chauvine@dta.com", LocalDate.of(1990, 05, 19), "test"));

    }

}
