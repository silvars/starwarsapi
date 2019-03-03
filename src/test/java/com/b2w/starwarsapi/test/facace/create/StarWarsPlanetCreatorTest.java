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

public class StarWarsPlanetCreatorTest extends StarWarsAbstractTest {

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
    public void setUp() throws PlanetNotFoundException, PlanetNameUninformedException {
        Mockito.when(starWarsPlanetService.planetExists(existe)).thenReturn(true);
        Mockito.when(starWarsPlanetService.planetExists(naoExiste)).thenReturn(false);

        planetVOWithNameExist.setName(existe);
        planetVOWithNameNotExist.setName(naoExiste);

        Mockito.when(starWarsPlanetAPIFetch.findPlanetByNameAPI(planetVOWithNameNotExist))
                .thenThrow(PlanetNotFoundException.class);
    }

    @Test
    public void createPlanet() throws PlanetNameUninformedException, PlanetAlreadyExistsException,
            PlanetNotFoundException, PlanetUninformedException {
        PlanetVO planetVO = new PlanetVO();
        planetVO.setName(naoExiste);
        planetVO.setApparitions(1);

        Mockito.when(starWarsPlanetService.planetExists(anyString())).thenReturn(false);
        Mockito.when(starWarsPlanetAPIFetch.findPlanetByNameAPI(any())).thenReturn(planetVO);
        Mockito.when(starWarsPlanetService.createPlanet(any())).thenReturn(createPlanetWithId());

        PlanetVO planetResult = starWarsPlanetCreator.createPlanet(planetVO);

        Assert.assertTrue(planetResult.getPlanetId().equals(1L));
        Assert.assertTrue(planetResult.getName().equals(naoExiste));
        Assert.assertTrue(planetResult.getApparitions().equals(1));
        Assert.assertTrue(planetResult.getTerrain().equals("terra"));
        Assert.assertTrue(planetResult.getClimate().equals("clima"));
    }

    @Test(expected = PlanetNotFoundException.class)
    public void createPlanetNotFoundAPI() throws PlanetNameUninformedException, PlanetAlreadyExistsException,
            PlanetNotFoundException, PlanetUninformedException {
        starWarsPlanetCreator.createPlanet(planetVOWithNameNotExist);
    }

    private Planet createPlanetWithId() {
        Planet planet = new Planet();

        planet.setPlanetId(1L);
        planet.setName(naoExiste);
        planet.setApparitions(1);
        planet.setTerrain("terra");
        planet.setClimate("clima");

        return planet;
    }
}
