package com.b2w.starwarsapi.test.api.fetch;

import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StarWarsPlanetFetchByExactNameControllerTest extends StarWarsAbstractTest {

    private MockMvc mockMvc;

    @MockBean
    private StarWarsPlanetFetch starWarsPlanetFetch;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void fetchPlanetByExactName() throws Exception {
        Mockito.when(starWarsPlanetFetch.fetchPlanetByExactName(anyString())).thenReturn(createPlanetVO());

        mockMvc.perform(get(PLANET_EXACT_NAME_URL, 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.planetId", is(1)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.terrain", is(terrain)))
                .andExpect(jsonPath("$.climate", is(climate)))
                .andExpect(jsonPath("$.apparitions", is(apparitions)));
    }

    @Test
    public void fetchByNamePlanetNotFound() throws Exception {
        Mockito.when(starWarsPlanetFetch.fetchPlanetByExactName(anyString())).thenThrow(PlanetNotFoundException.class);

        mockMvc.perform(get(PLANET_EXACT_NAME_URL, 1L))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }

    @Test
    public void fetchByIdPlanetNameUninformed() throws Exception {
        Mockito.when(starWarsPlanetFetch.fetchPlanetByExactName(anyString())).thenThrow(PlanetDataUninformedException.class);

        mockMvc.perform(get(PLANET_EXACT_NAME_URL, 1L))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isBadRequest());
    }
}
