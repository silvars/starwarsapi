package com.b2w.starwarsapi.test.facace.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.api.vo.ResultVO;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.feign.StarWarsPlanetFeign;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class StarWarsPlanetFetchAllAPITest extends StarWarsAbstractTest {

    @Autowired
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    @MockBean
    private StarWarsPlanetFeign starWarsPlanetFeign;

    @Test
    public void fetchAllPlanetsAPI() throws PlanetNotFoundException {
        Mockito.when(starWarsPlanetFeign.fetch(anyString(), anyString(), anyInt())).thenReturn(createResultVO());

        List<PlanetVO> planetVOS = starWarsPlanetAPIFetch.fetchAllPlanetsFromAPI();

        assertFalse(planetVOS.isEmpty());
        assertTrue("Não retornou nenhum planeta", planetVOS.size() > 0);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void fetchAllPlanetsAPINotFound() throws PlanetNotFoundException {
        Mockito.when(starWarsPlanetFeign.fetch(anyString(), anyString(), anyInt())).thenReturn(new ResultVO());

        starWarsPlanetAPIFetch.fetchAllPlanetsFromAPI();
    }

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

        planetVOS.add(PlanetVO.builder().name(name)
                .apparitions(apparitions)
                .climate(climate)
                .terrain(terrain)
                .films(new ArrayList<>())
                .planetId(planetId)
                .build());

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
