package com.b2w.starwarsapi.test.util;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.util.Validator;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Test;

public class ValidatorUtilTest extends StarWarsAbstractTest {

    @Test
    public void validatePlanetId() throws PlanetIdUninformedException {
        Validator.validatePlanetId(1L);
    }

    @Test(expected = PlanetIdUninformedException.class)
    public void validatePlanetIdUninformed() throws PlanetIdUninformedException {
        Validator.validatePlanetId(null);
    }

    @Test
    public void validatePlanetName() throws PlanetDataUninformedException {
        Validator.validatePlanetName(name);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetNameUninformed() throws PlanetDataUninformedException {
        String planetName = null;
        Validator.validatePlanetName(planetName);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetNameBlank() throws PlanetDataUninformedException {
        Validator.validatePlanetName("");
    }

    @Test
    public void validatePlanetTerrain() throws PlanetDataUninformedException {
        Validator.validatePlanetTerrain(terrain);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetTerrainUninformed() throws PlanetDataUninformedException {
        Validator.validatePlanetTerrain(null);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetTerrainBlank() throws PlanetDataUninformedException {
        Validator.validatePlanetTerrain("");
    }

    @Test
    public void validatePlanetClimate() throws PlanetDataUninformedException {
        Validator.validatePlanetClimate(climate);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetClimateUninformed() throws PlanetDataUninformedException {
        Validator.validatePlanetClimate(null);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetCLimateBlank() throws PlanetDataUninformedException {
        Validator.validatePlanetClimate("");
    }

    @Test
    public void validatePlanet() throws PlanetUninformedException, PlanetDataUninformedException {
        Validator.validatePlanetVO(PlanetVO.builder().name(name).climate(climate).terrain(terrain).build());
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetWithoutName() throws PlanetUninformedException, PlanetDataUninformedException {
        Validator.validatePlanetVO(PlanetVO.builder().climate(climate).terrain(terrain).build());
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetWithoutClimate() throws PlanetUninformedException, PlanetDataUninformedException {
        Validator.validatePlanetVO(PlanetVO.builder().name(name).terrain(terrain).build());
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void validatePlanetWithoutTerrain() throws PlanetUninformedException, PlanetDataUninformedException {
        Validator.validatePlanetVO(PlanetVO.builder().name(name).climate(climate).build());
    }

    @Test(expected = PlanetUninformedException.class)
    public void validatePlanetNull() throws PlanetUninformedException, PlanetDataUninformedException {
        Validator.validatePlanetVO(null);
    }
}
