package com.b2w.starwars.repository;

import com.b2w.starwars.entity.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StarWarsRepository extends CrudRepository<Planet, Long> {

    Optional<Planet> findByNameIgnoreCase(String name);

    Optional<Planet> findByNameContainingIgnoreCase(String name);
}
