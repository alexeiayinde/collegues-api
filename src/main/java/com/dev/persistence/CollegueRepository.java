package com.dev.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

    List<Collegue> findByNom(String nom);

}
