package com.dev.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = CollegueNonTrouveException.class)
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouveException ex, WebRequest request) {
        String bodyOfResponse = "Collègue non trouvé";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(value = CollegueInvalideException.class)
    protected ResponseEntity<Object> handleConflict(CollegueInvalideException ex, WebRequest request) {
        String bodyOfResponse = "Collègue invalide";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

}
