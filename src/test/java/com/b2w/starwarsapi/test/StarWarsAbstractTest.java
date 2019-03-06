package com.b2w.starwarsapi.test;

import com.b2w.starwars.StarwarsapiApplication;
import com.b2w.starwars.api.vo.PlanetVO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = StarwarsapiApplication.class)
public abstract class StarWarsAbstractTest {

    protected static final String PLANET_URL = "/planet";
    protected static final String PLANET_ID_URL = PLANET_URL + "/{planetId}";
    protected static final String PLANET_FETCH_ID_URL = PLANET_URL + "/byId/{id}";
    protected static final String PLANET_NAME_URL = PLANET_URL + "/byName/{name}";
    protected static final String PLANET_EXACT_NAME_URL = PLANET_URL + "/byExactName/{name}";
    protected static final String PLANET_FETCH_API_URL = PLANET_URL + "/fetchFromApi";

    protected static final String existe = "existe";
    protected static final String naoExiste = "naoExiste";

    protected static final Long planetId = 1L;
    protected static final Integer apparitions = 1;
    protected static final String name = "Terra";
    protected static final String climate = "climate";
    protected static final String terrain = "terrain";

    protected List<PlanetVO> createPlanetVOs() {
        List<PlanetVO> planetVOs = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 5; i++) {
            planetVOs.add(createPlanetVO(random.nextLong()));
        }
        return planetVOs;
    }

    protected PlanetVO createPlanetVO(Long planetId) {
        return PlanetVO.builder().name(name)
                .apparitions(apparitions)
                .climate(climate)
                .terrain(terrain)
                .planetId(planetId)
                .build();
    }

    protected PlanetVO createPlanetVO() {
        return PlanetVO.builder().name(name)
                .apparitions(apparitions)
                .climate(climate)
                .terrain(terrain)
                .planetId(planetId)
                .build();
    }
}
