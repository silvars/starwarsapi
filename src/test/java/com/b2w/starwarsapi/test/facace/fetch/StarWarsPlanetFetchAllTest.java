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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class StarWarsPlanetFetchAllTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetFetch starWarsPlanetFetch;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @Test
    public void fetchPlanetById() throws PlanetNotFoundException, PlanetIdUninformedException {
        Mockito.when(starWarsPlanetService.fetchAllPlanetsFromDatabase())
                .thenReturn(createPlanetList());

        List<PlanetVO> planetVOs = starWarsPlanetFetch.fetchAllPlanetsFromDatabase();

        assertNotNull(planetVOs);
        assertFalse(planetVOs.isEmpty());
        assertEquals(planetVOs.size(), 2);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void fetchPlanetByIdNotFound() throws PlanetNotFoundException {
        Mockito.when(starWarsPlanetService.fetchAllPlanetsFromDatabase()).thenReturn(null);
        starWarsPlanetFetch.fetchAllPlanetsFromDatabase();
    }

    private List<Planet> createPlanetList() {
        List<Planet> planets = new ArrayList<>();

        planets.add(Planet.builder().name(name)
                        .apparitions(apparitions)
                        .climate(climate)
                        .terrain(terrain)
                        .planetId(planetId)
                        .build());

        planets.add(Planet.builder().name(name)
                .apparitions(apparitions)
                .climate(climate)
                .terrain(terrain)
                .planetId(2L)
                .build());

        return planets;
    }
}
