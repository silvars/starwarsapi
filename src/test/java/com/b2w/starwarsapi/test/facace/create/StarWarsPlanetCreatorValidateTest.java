package com.b2w.starwarsapi.test.facace.create;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.exception.PlanetNameUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.facade.create.StarWarsPlanetCreator;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class StarWarsPlanetCreatorValidateTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetCreator starWarsPlanetCreator;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @MockBean
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    private String existe = "existe";
    private String naoExiste = "naoExiste";

    PlanetVO planetVOWithNameNotExist = new PlanetVO();
    PlanetVO planetVOWithNameExist= new PlanetVO();

    @Before
    public void setUp() {
        Mockito.when(starWarsPlanetService.planetExists(existe)).thenReturn(true);
        Mockito.when(starWarsPlanetService.planetExists(naoExiste)).thenReturn(false);

        planetVOWithNameExist.setName(existe);
        planetVOWithNameNotExist.setName(naoExiste);
    }

    @Test
    public void planetDontExist() throws PlanetAlreadyExistsException, PlanetNameUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(planetVOWithNameNotExist);
    }

    @Test(expected = PlanetAlreadyExistsException.class)
    public void planetExist() throws PlanetAlreadyExistsException, PlanetNameUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(planetVOWithNameExist);
    }

    @Test(expected = PlanetNameUninformedException.class)
    public void planetNameNull() throws PlanetAlreadyExistsException, PlanetNameUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(new PlanetVO());
    }

    @Test(expected = PlanetUninformedException.class)
    public void planetNull() throws PlanetAlreadyExistsException, PlanetNameUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(null);
    }
}
