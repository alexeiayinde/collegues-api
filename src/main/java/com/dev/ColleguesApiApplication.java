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
                new Collegue(UUID.randomUUID().toString(), "Ayinde", "Diana", "diana.ayinde@dta.com", LocalDate.of(1984, 05, 19),
                        "https://randomuser.me/api/portraits/women/11.jpg"));

        collegueService.creerCollegue(
                new Collegue(UUID.randomUUID().toString(), "Ayinde", "Liubov", "liubov.ayinde@dta.com", LocalDate.of(1960, 12, 01),
                        "https://randomuser.me/api/portraits/women/0.jpg"));

        collegueService.creerCollegue(
                new Collegue(UUID.randomUUID().toString(), "Chauvin", "Adrien", "adrien.chauvine@dta.com", LocalDate.of(1990, 05, 19),
                        "https://randomuser.me/api/portraits/men/85.jpg"));

    }

}
