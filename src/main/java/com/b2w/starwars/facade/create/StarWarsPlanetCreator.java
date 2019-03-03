package com.b2w.starwars.facade.create;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.exception.PlanetNameUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwars.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_DATABASE;

@Slf4j
@Component
public class StarWarsPlanetCreator {

    @Autowired
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    @Autowired
    private StarWarsPlanetService starWarsPlanetService;

    @CacheEvict(value = {CACHE_PLANETS_DATABASE}, allEntries = true)
    public PlanetVO createPlanet(PlanetVO planetVO)
            throws PlanetNotFoundException, PlanetAlreadyExistsException, PlanetNameUninformedException,
            PlanetUninformedException {
        validateIfExist(planetVO);
        planetVO = starWarsPlanetAPIFetch.findPlanetByNameAPI(planetVO);

        Planet planet = starWarsPlanetService.createPlanet(
                Planet.builder().name(planetVO.getName())
                        .terrain(planetVO.getTerrain())
                        .climate(planetVO.getClimate())
                        .apparitions(planetVO.getApparitions())
                        .build());

        return PlanetVO.builder().planetId(planet.getPlanetId())
                .name(planet.getName())
                .climate(planet.getClimate())
                .terrain(planet.getTerrain())
                .apparitions(planet.getApparitions())
                .build();
    }

    public void validateIfExist(PlanetVO planetVO)
            throws PlanetAlreadyExistsException, PlanetNameUninformedException, PlanetUninformedException {
        log.info("I=Verificando se o planeta já não está cadastrado, planetVo={}", planetVO);
        Validator.validatePlanetVO(planetVO);
        Validator.validatePlanetName(planetVO.getName());
        if(starWarsPlanetService.planetExists(planetVO.getName())) {
            log.error("E=Planeta já cadastrado, planetVO={}", planetVO);
            throw new PlanetAlreadyExistsException("Planeta já cadastrado");
        }
    }
}
