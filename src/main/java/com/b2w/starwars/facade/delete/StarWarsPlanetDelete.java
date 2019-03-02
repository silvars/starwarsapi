package com.b2w.starwars.facade.delete;

import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.service.StarWarsPlanetService;
import com.b2w.starwars.util.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class StarWarsPlanetDelete {

    @Autowired
    private StarWarsPlanetService starWarsPlanetService;

    public void deletePlanet(Long planetId) throws PlanetNotFoundException, PlanetIdUninformedException {
        Validator.validatePlanetId(planetId);
        Planet planet = starWarsPlanetService.fetchPlanetById(planetId);
        starWarsPlanetService.deletePlanet(planet);
    }
}
