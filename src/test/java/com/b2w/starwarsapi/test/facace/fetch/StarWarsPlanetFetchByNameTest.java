package com.b2w.starwarsapi.test.facace.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetFetch;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class StarWarsPlanetFetchByNameTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetFetch starWarsPlanetFetch;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @Test
    public void fetchPlanetByName() throws PlanetNotFoundException, PlanetDataUninformedException {
        Mockito.when(starWarsPlanetService.fetchPlanetByName(anyString()))
                .thenReturn(Planet.builder()
                        .name(name)
                        .apparitions(apparitions)
                        .climate(climate)
                        .terrain(terrain)
                        .planetId(planetId)
                        .build());

        PlanetVO planetVO = starWarsPlanetFetch.fetchPlanetByName(name);

        assertEquals(planetVO.getPlanetId(), planetId);
        assertEquals(planetVO.getName(), name);
        assertEquals(planetVO.getApparitions(), apparitions);
        assertEquals(planetVO.getClimate(), climate);
        assertEquals(planetVO.getTerrain(), terrain);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void fetchPlanetByNameNotFound() throws PlanetNotFoundException, PlanetDataUninformedException {
        Mockito.when(starWarsPlanetService.fetchPlanetByName(anyString()))
                .thenThrow(PlanetNotFoundException.class);

        starWarsPlanetFetch.fetchPlanetByName(RandomStringUtils.random(10));
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void fetchPlanetByNameNull() throws PlanetNotFoundException, PlanetDataUninformedException {
        starWarsPlanetFetch.fetchPlanetByName(null);
    }

    @Test(expected = PlanetDataUninformedException.class)
    public void fetchPlanetByNameBlank() throws PlanetNotFoundException, PlanetDataUninformedException {
        starWarsPlanetFetch.fetchPlanetByName("");
    }
}
