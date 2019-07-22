package com.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.security.InfosAuthentification;
import com.dev.service.CollegueService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(path = "/collegues")
public class AuthentificationCtrl {

    @Value("${jwt.expires_in}")
    private Integer EXPIRES_IN;

    @Value("${jwt.cookie}")
    private String TOKEN_COOKIE;

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private CollegueService collegueService;

    private PasswordEncoder passwordEncoder;

    public AuthentificationCtrl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> authenticate(@RequestBody InfosAuthentification infos) {
        return collegueService.findByNomUtilisateur(infos.getNomUtilisateur())
                .filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
                .map(utilisateur -> {
                    Map<String, Object> infosSupplementaireToken = new HashMap<>();
                    infosSupplementaireToken.put("roles", utilisateur.getRoles());

                    String jetonJWT = Jwts.builder()
                            .setSubject(utilisateur.getNomUtilisateur())
                            .addClaims(infosSupplementaireToken)
                            .setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
                            .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET)
                            .compact();

                    ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT)
                            .httpOnly(true)
                            .maxAge(EXPIRES_IN * 1000)
                            .path("/")
                            .build();

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie.toString())
                            .build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
