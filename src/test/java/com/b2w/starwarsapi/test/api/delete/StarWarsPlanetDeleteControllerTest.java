package com.b2w.starwarsapi.test.api.delete;

import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.facade.delete.StarWarsPlanetDelete;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StarWarsPlanetDeleteControllerTest extends StarWarsAbstractTest {

    private MockMvc mockMvc;

    @MockBean
    private StarWarsPlanetDelete starWarsPlanetDelete;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deletePlanet() throws Exception {
        doNothing().when(starWarsPlanetDelete).deletePlanet(anyLong());

        mockMvc.perform(delete("/planet/{planetId}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isAccepted());
    }

    @Test
    public void deletePlanetNotFound() throws Exception {
        doThrow(PlanetNotFoundException.class).when(starWarsPlanetDelete).deletePlanet(anyLong());

        mockMvc.perform(delete("/planet/{planetId}", 1L))
                .andExpect(status().is4xxClientError())
                .andExpect(status().isNotFound());
    }
}
