package com.b2w.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class PlanetNotFoundException extends Exception {

    public PlanetNotFoundException(String message) {
        super(message);
    }
}
