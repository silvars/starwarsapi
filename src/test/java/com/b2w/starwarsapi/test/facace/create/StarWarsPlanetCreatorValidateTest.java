package com.b2w.starwarsapi.test.facace.create;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.facade.create.StarWarsPlanetCreator;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

public class StarWarsPlanetCreatorValidateTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetCreator starWarsPlanetCreator;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @MockBean
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

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
    public void planetDontExist() throws PlanetAlreadyExistsException, PlanetDataUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(planetVOWithNameNotExist);
    }

    @Test(expected = PlanetAlreadyExistsException.class)
    public void planetExist() throws PlanetAlreadyExistsException, PlanetDataUninformedException,
            PlanetUninformedException {
        starWarsPlanetCreator.validateIfExist(planetVOWithNameExist);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void planetNameNull() throws PlanetAlreadyExistsException,
            PlanetUninformedException, PlanetDataUninformedException {
        starWarsPlanetCreator.validateIfExist(new PlanetVO());
    }

    @Test(expected = PlanetUninformedException.class)
    public void planetNull() throws PlanetAlreadyExistsException,
            PlanetUninformedException, PlanetDataUninformedException {
        starWarsPlanetCreator.validateIfExist(null);
    }
}
