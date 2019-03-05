package com.b2w.starwarsapi.test.facace.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetFetch;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

public class StarWarsPlanetFetchByIdTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetFetch starWarsPlanetFetch;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @Test
    public void fetchPlanetById() throws PlanetNotFoundException, PlanetIdUninformedException {
        Mockito.when(starWarsPlanetService.fetchPlanetById(anyLong()))
                .thenReturn(Planet.builder()
                        .name(name)
                        .apparitions(apparitions)
                        .climate(climate)
                        .terrain(terrain)
                        .planetId(planetId)
                        .build());

        PlanetVO planetVO = starWarsPlanetFetch.fetchPlanetById(1L);

        assertEquals(planetVO.getPlanetId(), planetId);
        assertEquals(planetVO.getName(), name);
        assertEquals(planetVO.getApparitions(), apparitions);
        assertEquals(planetVO.getClimate(), climate);
        assertEquals(planetVO.getTerrain(), terrain);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void fetchPlanetByIdNotFound() throws PlanetNotFoundException, PlanetIdUninformedException {
        Mockito.when(starWarsPlanetService.fetchPlanetById(anyLong()))
                .thenThrow(PlanetNotFoundException.class);

        starWarsPlanetFetch.fetchPlanetById(new Random().nextLong());
    }

    @Test(expected = PlanetIdUninformedException.class)
    public void fetchPlanetByIdNull() throws PlanetNotFoundException, PlanetIdUninformedException {
        starWarsPlanetFetch.fetchPlanetById(null);
    }
}
