package com.dev.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.entite.Collegue;
import com.dev.entite.PhotoDTO;
import com.dev.exception.CollegueInvalideException;
import com.dev.exception.CollegueNonTrouveException;
import com.dev.persistence.CollegueRepository;

@Service
public class CollegueService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CollegueRepository collegueRepository;

    private static final int AGE_MINIMUM = 18;
    private static final int TAILLE_EMAIL_MINIMUM = 3;
    private static final int TAILLE_PRENOM_MINIMUM = 2;
    private static final int TAILLE_NOM_MINIMUM = 2;

    public CollegueService() {
    }

    @PostConstruct
    public void init() {
        Collegue c1 = new Collegue(UUID.randomUUID().toString(), "Ayinde", "Alexei", "alexei.ayinde@dta.com", LocalDate.of(1987, 10, 01),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/President_Barack_Obama.jpg/220px-President_Barack_Obama.jpg", "alexei01",
                passwordEncoder.encode("alexeipass"), Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        collegueRepository.save(c1);
        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "Ayinde", "Diana", "diana.ayinde@dta.com",
                LocalDate.of(1984, 05, 19),
                "https://randomuser.me/api/portraits/women/11.jpg", "diana01", passwordEncoder.encode("dianapass"), Arrays.asList("ROLE_USER")));
        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "Ayinde", "Liubov", "liubov.ayinde@dta.com", LocalDate.of(1960, 12, 01),
                "https://randomuser.me/api/portraits/women/0.jpg", "liubov01", passwordEncoder.encode("liubovpass"), Arrays.asList("ROLE_USER")));
        collegueRepository.save(new Collegue(UUID.randomUUID().toString(), "Chauvin", "Adrien", "adrien.chauvin@dta.com", LocalDate.of(1990, 05, 19),
                "https://randomuser.me/api/portraits/men/85.jpg", "adrien01", passwordEncoder.encode("adrienpass"), Arrays.asList("ROLE_USER")));
    }

    public List<Collegue> listerCollegues() {
        return collegueRepository.findAll();
    }

    public List<Collegue> rechercherParNom(String nom) {
        return collegueRepository.findByNom(nom);
    }

    public Optional<Collegue> findByNomUtilisateur(String nom) {
        return collegueRepository.findByNomUtilisateur(nom);
    }

    public Optional<Collegue> rechercherParMatricule(String matricule) {
        return collegueRepository.findById(matricule);
    }

    public Collegue creerCollegue(Collegue collegue) {

        if (collegue.getNom().trim().length() < TAILLE_NOM_MINIMUM) {
            throw new CollegueInvalideException("Nom invalide (min. " + TAILLE_NOM_MINIMUM + " caractères)");
        }
        if (collegue.getPrenoms().trim().length() < TAILLE_PRENOM_MINIMUM) {
            throw new CollegueInvalideException("Prénom(s) invalide(s) (min. " + TAILLE_PRENOM_MINIMUM + "caractères)");
        }
        if (collegue.getEmail().trim().length() < TAILLE_EMAIL_MINIMUM) {
            throw new CollegueInvalideException("Email invalide (min. " + TAILLE_EMAIL_MINIMUM + " caractères)");
        }
        if (!collegue.getEmail().contains("@")) {
            throw new CollegueInvalideException("Email invalide (doit contenir le caractère '@')");
        }
        if (Period.between(collegue.getDateDeNaissance(), LocalDate.now()).getYears() < AGE_MINIMUM) {
            throw new CollegueInvalideException("Date de naissance invalide (l'âge minimum est de " + AGE_MINIMUM + ")");
        }
        if (!collegue.getPhotoUrl().startsWith("http://") && !collegue.getPhotoUrl().startsWith("https://")) {
            throw new CollegueInvalideException("L'URL de la photo invalide (doit commencer par 'http://' ou 'https://')");
        }
        collegue.setMatricule(UUID.randomUUID().toString());
        return collegueRepository.save(collegue); // TODO crypter mdp
    }

    public Collegue modifierEmail(String matricule, String email) {
        Collegue c = rechercherParMatricule(matricule)
                .orElseThrow(() -> new CollegueNonTrouveException());
        if (email.trim().length() >= TAILLE_EMAIL_MINIMUM && email.contains("@")) {
            c.setEmail(email);
            return collegueRepository.save(c);
        }
        throw new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : email (doit contenir un caractère @)");
    }

    public Collegue modifierPhotoUrl(String matricule, String photoUrl) {
        Collegue c = rechercherParMatricule(matricule)
                .orElseThrow(() -> new CollegueNonTrouveException());
        if (photoUrl.startsWith("http")) {
            c.setPhotoUrl(photoUrl);
            return collegueRepository.save(c);
        }
        throw new CollegueInvalideException("Veuillez saisir de nouveau le paramètre suivant : photoUrl (doit commencer par 'http')");
    }

    public List<PhotoDTO> rechercherPhotos() {
        List<PhotoDTO> listePhotos = new ArrayList<>();
        for (Collegue collegue : collegueRepository.findAll()) {
            listePhotos.add(new PhotoDTO(collegue.getMatricule(), collegue.getPhotoUrl()));
        }
        return listePhotos;
    }

}
