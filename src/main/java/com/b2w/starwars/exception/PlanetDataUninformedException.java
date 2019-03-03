package com.b2w.starwars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class PlanetDataUninformedException extends Exception {

    public PlanetDataUninformedException(String message) {
        super(message);
    }
}
