package com.b2w.starwars.api;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.create.StarWarsPlanetCreator;
import com.b2w.starwars.facade.delete.StarWarsPlanetDelete;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.facade.fetch.StarWarsPlanetFetch;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/planet")
public class StarWarsController {

    @Autowired
    private StarWarsPlanetCreator starWarsPlanetCreator;

    @Autowired
    private StarWarsPlanetDelete starWarsPlanetDelete;

    @Autowired
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    @Autowired
    private StarWarsPlanetFetch starWarsPlanetFetch;

    /**
     * Adicionar um planeta (com nome, clima e terreno)
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Cria um novo planeta no banco de dados"
    )
    public PlanetVO createPlanet(@RequestBody PlanetVO planetVO) throws PlanetNotFoundException,
            PlanetAlreadyExistsException {
        log.info("I=Criando um novo planeta, planetVO={}", planetVO);
        return starWarsPlanetCreator.createPlanet(planetVO);
    }

    /**
     * Listar planetas do banco de dados
     * @return
     */
    @GetMapping
    @ApiOperation(
            value = "Lista todos os planetas contidos no banco de dados"
    )
    public List<PlanetVO> fetchAllPlanetsFromDatabase() {
        log.info("I=Buscando todos os planetas no banco de dados");
        return starWarsPlanetFetch.fetchAllPlanetsFromDatabase();
    }

    /**
     * Listar planetas da API do Star Wars
     * @return
     */
    @GetMapping("/fecthFromApi")
    @ApiOperation(
            value = "Lista todos os planetas contidos na API do Star Wars"
    )
    public List<PlanetVO> fetchAllPlanetsFromAPI() {
        log.info("I=Buscando todos os planetas na API");
        return starWarsPlanetAPIFetch.fetchAllPlanetsFromAPI();
    }

    /**
     * Buscar por nome no banco de dados
     *
     * @param name - nome do planeta
     *
     * @return Planet
     */
    @GetMapping("/byName/{name}")
    @ApiOperation(
            value = "Lista um planeta dado o seu nome"
    )
    public PlanetVO fetchPlanetFromDatabaseByName(@PathVariable("name") String name) throws PlanetNotFoundException {
        log.info("I=Buscando planeta pelo nome, name={}", name);
        return starWarsPlanetFetch.fetchPlanetByName(name);
    }

    /**
     * Buscar por nome no banco de dados
     *
     * @param name - nome do planeta
     *
     * @return Planet
     */
    @GetMapping("/byExactName/{name}")
    @ApiOperation(
            value = "Lista um planeta dado o seu nome"
    )
    public PlanetVO fetchPlanetFromDatabaseByExactName(@PathVariable("name") String name) throws PlanetNotFoundException {
        log.info("I=Buscando planeta pelo nome, name={}", name);
        return starWarsPlanetFetch.fetchPlanetByExactName(name);
    }

    /**
     * Buscar por ID no banco de dados
     *
     * @param planetId - Id do planeta no banco de dados
     *
     * @return Planet
     */
    @GetMapping("/byId/{id}")
    @ApiOperation(
            value = "Lista um planeta dado o seu ID"
    )
    public PlanetVO fetchPlanetFromDatabaseById(@PathVariable("id") Long planetId) throws PlanetNotFoundException {
        log.info("I=Buscando planeta pelo ID, planetId={}", planetId);
        return starWarsPlanetFetch.fetchPlanetById(planetId);
    }

    /**
     * Remover planeta
     */
    @DeleteMapping("/{planetId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(
            value = "Remove um planeta"
    )
    public void deletePlanet(@PathVariable Long planetId) throws PlanetNotFoundException {
        log.info("I=Removendo um planeta, planetId={}", planetId);
        starWarsPlanetDelete.deletePlanet(planetId);
    }
}
