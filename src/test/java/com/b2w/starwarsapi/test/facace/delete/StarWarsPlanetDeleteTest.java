package com.b2w.starwarsapi.test.facace.delete;

import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.delete.StarWarsPlanetDelete;
import com.b2w.starwars.feign.StarWarsPlanetFeign;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

public class StarWarsPlanetDeleteTest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetDelete starWarsPlanetDelete;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    @Test(expected = PlanetIdUninformedException.class)
    public void deletePlanetIdUninformed() throws PlanetNotFoundException, PlanetIdUninformedException {
        starWarsPlanetDelete.deletePlanet(null);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void deletePlanetNotFound() throws PlanetNotFoundException, PlanetIdUninformedException {
        Mockito.when(starWarsPlanetService.fetchPlanetById(anyLong())).thenThrow(PlanetNotFoundException.class);
        starWarsPlanetDelete.deletePlanet(1L);
    }
}
