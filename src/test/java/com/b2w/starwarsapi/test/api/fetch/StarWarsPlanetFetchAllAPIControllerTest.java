package com.b2w.starwarsapi.test.api.fetch;

import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.fetch.StarWarsPlanetAPIFetch;
import com.b2w.starwars.facade.fetch.StarWarsPlanetFetch;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StarWarsPlanetFetchAllAPIControllerTest extends StarWarsAbstractTest {

    private MockMvc mockMvc;

    @MockBean
    private StarWarsPlanetAPIFetch starWarsPlanetAPIFetch;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void fetchAllPlanetsDatabase() throws Exception {
        Mockito.when(starWarsPlanetAPIFetch.fetchAllPlanetsFromAPI()).thenReturn(createPlanetVOs());

        mockMvc.perform(get(PLANET_FETCH_API_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    public void fetchByNamePlanetNotFound() throws Exception {
        Mockito.when(starWarsPlanetAPIFetch.fetchAllPlanetsFromAPI()).thenThrow(PlanetNotFoundException.class);

        mockMvc.perform(get(PLANET_FETCH_API_URL))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }
}
