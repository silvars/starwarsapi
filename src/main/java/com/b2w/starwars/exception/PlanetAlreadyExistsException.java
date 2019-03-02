package com.b2w.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class PlanetAlreadyExistsException extends Exception {

    public PlanetAlreadyExistsException(String message) {
        super(message);
    }
}
