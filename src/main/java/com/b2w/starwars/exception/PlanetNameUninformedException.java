package com.b2w.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class PlanetNameUninformedException extends Exception {

    public PlanetNameUninformedException(String message) {
        super(message);
    }
}
