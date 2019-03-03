package com.b2w.starwarsapi.test.util;

import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.util.Validator;
import org.junit.Test;

public class ValidatorUtilTest {

    @Test
    public void validatePlanetId() throws PlanetIdUninformedException {
        Validator.validatePlanetId(1L);
    }

    @Test(expected = PlanetIdUninformedException.class)
    public void validatePlanetIdUninformed() throws PlanetIdUninformedException {
        Validator.validatePlanetId(null);
    }

    @Test
    public void validatePlanetName() throws PlanetIdUninformedException {
        Validator.validatePlanetName("nome");
    }

    @Test(expected = PlanetIdUninformedException.class)
    public void validatePlanetNameUninformed() throws PlanetIdUninformedException {
        Validator.validatePlanetName(null);
    }

    @Test(expected = PlanetIdUninformedException.class)
    public void validatePlanetNameBlank() throws PlanetIdUninformedException {
        Validator.validatePlanetName("");
    }
}
