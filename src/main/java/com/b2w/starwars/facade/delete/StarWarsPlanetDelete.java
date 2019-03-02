package com.b2w.starwars.facade.delete;

import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.service.StarWarsPlanetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StarWarsPlanetDelete {

    @Autowired
    private StarWarsPlanetService starWarsPlanetService;

    public void deletePlanet(Long planetId) throws PlanetNotFoundException {
        Planet planet = starWarsPlanetService.fetchPlanetById(planetId);
        starWarsPlanetService.deletePlanet(planet);
    }
}
