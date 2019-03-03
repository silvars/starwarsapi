package com.b2w.starwarsapi.test.util;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNameUninformedException;
import com.b2w.starwars.exception.PlanetUninformedException;
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
    public void validatePlanetName() throws PlanetNameUninformedException {
        Validator.validatePlanetName("nome");
    }

    @Test(expected = PlanetNameUninformedException.class)
    public void validatePlanetNameUninformed() throws PlanetNameUninformedException {
        Validator.validatePlanetName(null);
    }

    @Test(expected = PlanetNameUninformedException.class)
    public void validatePlanetNameBlank() throws PlanetNameUninformedException {
        Validator.validatePlanetName("");
    }

    @Test
    public void validatePlanet() throws PlanetUninformedException {
        Validator.validatePlanetVO(new PlanetVO());
    }

    @Test(expected = PlanetUninformedException.class)
    public void validatePlanetNull() throws PlanetUninformedException {
        Validator.validatePlanetVO(null);
    }
}
