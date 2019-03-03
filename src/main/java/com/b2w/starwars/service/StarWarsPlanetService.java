package com.b2w.starwars.service;

import com.b2w.starwars.entity.Planet;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.repository.StarWarsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_EXACT_NAME;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_ID;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_BY_NAME;
import static com.b2w.starwars.CacheConfiguration.CACHE_PLANETS_DATABASE;

@Slf4j
@Service
public class StarWarsPlanetService {

    @Autowired
    private StarWarsRepository starWarsRepository;

    @Transactional(readOnly = true)
    public Planet fetchPlanetByName(String name) throws PlanetNotFoundException {
        return starWarsRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new PlanetNotFoundException("Planeta não encontrado"));
    }

    @Transactional(readOnly = true)
    public Planet fetchPlanetByExactName(String name) throws PlanetNotFoundException {
        return starWarsRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new PlanetNotFoundException("Planeta não encontrado"));
    }

    @Transactional(readOnly = true)
    public Boolean planetExists(String name) {
        return starWarsRepository.findByNameIgnoreCase(name).isPresent();
    }

    @Transactional(readOnly = true)
    public Planet fetchPlanetById(Long planetId) throws PlanetNotFoundException {
        return starWarsRepository.findById(planetId)
                .orElseThrow(() -> new PlanetNotFoundException("Planeta não encontrado"));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Planet createPlanet(Planet planet) {
        return starWarsRepository.save(planet);
    }

    @Transactional(readOnly = true)
    public Iterable<Planet> fetchAllPlanetsFromDatabase() {
        return starWarsRepository.findAll();
    }

    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = CACHE_PLANETS_DATABASE, allEntries = true),
                    @CacheEvict(value = CACHE_PLANETS_BY_NAME, key = "#planet.name"),
                    @CacheEvict(value = CACHE_PLANETS_BY_EXACT_NAME, key = "#planet.name"),
                    @CacheEvict(value = CACHE_PLANETS_BY_ID, key = "#planet.planetId")
            }
    )
    public void deletePlanet(Planet planet) {
        starWarsRepository.delete(planet);
    }
}
