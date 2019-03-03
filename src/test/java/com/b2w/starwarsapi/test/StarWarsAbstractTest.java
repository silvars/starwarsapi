package com.b2w.starwarsapi.test;

import com.b2w.starwars.StarwarsapiApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = StarwarsapiApplication.class)
public abstract class StarWarsAbstractTest {

    protected static final String existe = "existe";
    protected static final String naoExiste = "naoExiste";
    protected static final String climate = "climate";
    protected static final String terrain = "terrain";
}
