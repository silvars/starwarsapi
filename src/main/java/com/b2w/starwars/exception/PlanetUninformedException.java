package com.b2w.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class PlanetUninformedException extends Exception {

    public PlanetUninformedException(String message) {
        super(message);
    }
}
