package com.b2w.starwars.facade.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.api.vo.ResultVO;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.feign.StarWarsPlanetFeign;
import com.b2w.starwars.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_API;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_NAME_API;

@Slf4j
@Component
public class StarWarsPlanetAPIFetch {

    @Value("${starwars.userAgent}")
    private String userAgent;

    @Autowired
    private StarWarsPlanetFeign starWarsPlanetFeign;

    @Cacheable(value = CACHE_PLANETS_BY_NAME_API, key = "#planetVO.name")
    public PlanetVO findPlanetByNameAPI(PlanetVO planetVO)
            throws PlanetNotFoundException, PlanetDataUninformedException, PlanetUninformedException {
        Validator.validatePlanetName(planetVO);
        log.info("I=Buscando planeta na API por nome, planetVO={}", planetVO);
        ResultVO results = starWarsPlanetFeign.fetchByName(MediaType.APPLICATION_JSON.toString(),
                userAgent, planetVO.getName());

        if(results.getResults().isEmpty()) {
            log.error("E=Planeta não encontrado na API StarWars, planetVO={}", planetVO);
            throw new PlanetNotFoundException("Planeta não encontrado na API StarWars");
        }

        results.getResults().stream().forEach((planet) -> {
            if(planet.getName().equalsIgnoreCase(planetVO.getName())) {
                planetVO.setApparitions(planet.getFilms().size());
            }
        });

        if(planetVO.getApparitions() == null) {
            log.error("E=Planeta não encontrado na API StarWars, planetVO={}", planetVO);
            throw new PlanetNotFoundException("Planeta não encontrado na API StarWars");
        }

        return planetVO;
    }

    @Cacheable(CACHE_PLANETS_API)
    public List<PlanetVO> fetchAllPlanetsFromAPI() throws PlanetNotFoundException {
        List<PlanetVO> planetVOS = new ArrayList<>();
        Integer page = 1;

        ResultVO result = starWarsPlanetFeign.fetch(MediaType.APPLICATION_JSON.toString(), userAgent, page);

        if(CollectionUtils.isEmpty(result.getResults())) {
            throw new PlanetNotFoundException("Não retornou nenhum planeta");
        }

        planetVOS.addAll(createPlanet(result));

        if(result.hasNext()) {
            fetchAllPlanetsFromAPI(++page, planetVOS);
        }

        return planetVOS;
    }

    private void fetchAllPlanetsFromAPI(Integer page, List<PlanetVO> planetVOS) {
        ResultVO result = starWarsPlanetFeign.fetch(MediaType.APPLICATION_JSON.toString(), userAgent, page);

        planetVOS.addAll(createPlanet(result));

        if(result.hasNext()) {
            fetchAllPlanetsFromAPI(++page, planetVOS);
        }
    }

    private List<PlanetVO> createPlanet(ResultVO resultVO) {
        List<PlanetVO> planetVOS = new ArrayList<>();

        resultVO.getResults().stream().forEach((planetVO -> {
            planetVOS.add(PlanetVO.builder().planetId(planetVO.getPlanetId())
                    .name(planetVO.getName())
                    .terrain(planetVO.getTerrain())
                    .climate(planetVO.getClimate())
                    .apparitions(CollectionUtils.isNotEmpty(planetVO.getFilms()) ? planetVO.getFilms().size() : 0)
                    .build());
        }));

        return planetVOS;
    }
}
