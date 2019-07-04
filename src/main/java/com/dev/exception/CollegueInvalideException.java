package com.dev.exception;

public class CollegueInvalideException extends Exception {

    public CollegueInvalideException() {
        super();
    }

    public CollegueInvalideException(String str) {
        super("Veuillez saisir de nouveau le paramètre suivant : " + str);
    }

}
