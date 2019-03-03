package com.b2w.starwars.facade.fetch;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.feign.StarWarsPlanetFeign;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwars.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_EXACT_NAME;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_ID;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_NAME;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_DATABASE;

@Slf4j
@Component
public class StarWarsPlanetFetch {

    @Value("${starwars.userAgent}")
    private String userAgent;

    @Autowired
    private StarWarsPlanetService starWarsPlanetService;

    @Autowired
    private StarWarsPlanetFeign starWarsPlanetFeign;

    @Cacheable(value = CACHE_PLANETS_BY_NAME)
    public PlanetVO fetchPlanetByName(String name) throws PlanetNotFoundException, PlanetDataUninformedException {
        Validator.validatePlanetName(name);
        Planet planet = starWarsPlanetService.fetchPlanetByName(name);

        return PlanetVO.builder().planetId(planet.getPlanetId())
                .name(planet.getName())
                .terrain(planet.getTerrain())
                .climate(planet.getClimate())
                .apparitions(planet.getApparitions())
                .build();
    }

    @Cacheable(value = CACHE_PLANETS_BY_EXACT_NAME)
    public PlanetVO fetchPlanetByExactName(String name) throws PlanetNotFoundException, PlanetDataUninformedException {
        Validator.validatePlanetName(name);
        Planet planet = starWarsPlanetService.fetchPlanetByExactName(name);

        return PlanetVO.builder().planetId(planet.getPlanetId())
                .name(planet.getName())
                .terrain(planet.getTerrain())
                .climate(planet.getClimate())
                .apparitions(planet.getApparitions())
                .build();
    }

    @Cacheable(CACHE_PLANETS_BY_ID)
    public PlanetVO fetchPlanetById(Long planetId) throws PlanetNotFoundException, PlanetIdUninformedException {
        Validator.validatePlanetId(planetId);
        Planet planet = starWarsPlanetService.fetchPlanetById(planetId);

        return PlanetVO.builder().planetId(planet.getPlanetId())
                .name(planet.getName())
                .terrain(planet.getTerrain())
                .climate(planet.getClimate())
                .apparitions(planet.getApparitions())
                .build();
    }

    @Cacheable(CACHE_PLANETS_DATABASE)
    public List<PlanetVO> fetchAllPlanetsFromDatabase() throws PlanetNotFoundException {
        List<PlanetVO> planetVOs = new ArrayList<>();

        Iterable<Planet> planets = starWarsPlanetService.fetchAllPlanetsFromDatabase();

        if(IterableUtils.isEmpty(planets)) {
            throw new PlanetNotFoundException("Nenhum planeta encontrado");
        }

        planets.forEach(planet -> planetVOs.add(
                PlanetVO.builder().planetId(planet.getPlanetId())
                        .name(planet.getName())
                        .terrain(planet.getTerrain())
                        .climate(planet.getClimate())
                        .apparitions(planet.getApparitions())
                        .build()
        ));

        return planetVOs;
    }
}
