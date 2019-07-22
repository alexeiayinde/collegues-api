package com.dev.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entite.Collegue;

@Repository
public interface CollegueRepository extends JpaRepository<Collegue, String> {

    List<Collegue> findByNom(String nom);

    Optional<Collegue> findByNomUtilisateur(String nomUtilisateur);

}
