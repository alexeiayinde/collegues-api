package com.dev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dev.exception.CollegueInvalideException;
import com.dev.exception.CollegueNonTrouveException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = CollegueNonTrouveException.class)
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouveException ex, WebRequest request) {
        String bodyOfResponse = "Collègue non trouvé";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(value = CollegueInvalideException.class)
    protected ResponseEntity<Object> handleConflict(CollegueInvalideException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Collègue invalide ! " + ex.getMessage());
    }

}
