package com.b2w.starwarsapi.test.facace.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.api.vo.ResultVO;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.feign.StarWarsPlanetFeign;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

public class StarWarsPlanetFetchByNameAPITest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    @MockBean
    private StarWarsPlanetFeign starWarsPlanetFeign;

    @Test
    public void fetchAllPlanetsAPI() throws PlanetNotFoundException, PlanetDataUninformedException, PlanetUninformedException {
        Mockito.when(starWarsPlanetFeign.fetchByName(anyString(), anyString(), anyString()))
                .thenReturn(createResultVO());

        PlanetVO planetVO = starWarsPlanetAPIFetch.findPlanetByNameAPI(createPlanetVO());

        assertNotNull(planetVO);
        assertEquals(planetVO.getName(), name);
        assertEquals(planetVO.getClimate(), climate);
        assertEquals(planetVO.getTerrain(), terrain);
    }

    // result null
    // result vazio
    // nome não encontrado nos resultados


    private ResultVO createResultVO() {
        ResultVO resultVO = new ResultVO();

        resultVO.setResults(createPlanetVOList());

        return resultVO;
    }

    private ResultVO createResultVOWithNext() {
        ResultVO resultVO = new ResultVO();
        resultVO.setNext("temProximo");

        resultVO.setResults(createPlanetVOList());

        return resultVO;
    }

    private List<PlanetVO> createPlanetVOList() {
        List<PlanetVO> planetVOS = new ArrayList<>();

        planetVOS.add(createPlanetVO());

        planetVOS.add(PlanetVO.builder().name(name)
                .apparitions(apparitions)
                .climate(climate)
                .terrain(terrain)
                .films(new ArrayList<>())
                .planetId(2L)
                .build());

        return planetVOS;
    }
}
