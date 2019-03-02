package com.b2w.starwars.util;

import com.b2w.starwars.exception.PlanetIdUninformedException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class Validator {

    public static void validatePlanetId(Long planetId) throws PlanetIdUninformedException {
        try {
            Objects.requireNonNull(planetId);
        } catch (NullPointerException e) {
            log.error("Id do planeta não foi informado");
            throw new PlanetIdUninformedException("Id do planeta não foi informado");
        }
    }

    public static void validatePlanetName(String planetName) throws PlanetIdUninformedException {
        try {
            Objects.requireNonNull(planetName);
        } catch (NullPointerException e) {
            log.error("Nome do planeta não foi informado");
            throw new PlanetIdUninformedException("Nome do planeta não foi informado");
        }
    }
}
