package com.b2w.starwarsapi.test.facace;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.facade.create.StarWarsPlanetCreator;
import com.b2w.starwars.service.StarWarsPlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StarWarsPlanetCreatorTest {

    @Autowired
    private StarWarsPlanetCreator starWarsPlanetCreator;

    @MockBean
    private StarWarsPlanetService starWarsPlanetService;

    private String existe = "existe";
    private String naoExiste = "naoExiste";

    @Before
    public void setUp() {
        Mockito.when(starWarsPlanetService.planetExists(existe)).thenReturn(true);
        Mockito.when(starWarsPlanetService.planetExists(naoExiste)).thenReturn(false);
    }

    @Test
    public void planetExist() throws PlanetAlreadyExistsException {
        PlanetVO planetVO = new PlanetVO();
        planetVO.setName(existe);

        starWarsPlanetCreator.validateIfExist(planetVO);
    }

    @Test
    public void planetDontExist() throws PlanetAlreadyExistsException {
        PlanetVO planetVO = new PlanetVO();
        planetVO.setName(naoExiste);

        starWarsPlanetCreator.validateIfExist(planetVO);
    }
}
