package com.b2w.starwarsapi.test.api.create;

import com.b2w.starwars.exception.PlanetAlreadyExistsException;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetNotFoundException;
import com.b2w.starwars.exception.PlanetUninformedException;
import com.b2w.starwars.facade.create.StarWarsPlanetCreator;
import com.b2w.starwars.facade.delete.StarWarsPlanetDelete;
import com.b2w.starwarsapi.test.StarWarsAbstractTest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StarWarsPlanetCreateControllerTest extends StarWarsAbstractTest {

    private MockMvc mockMvc;

    @MockBean
    private StarWarsPlanetCreator starWarsPlanetCreator;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Test
    public void createPlanet() throws Exception {
        Mockito.when(starWarsPlanetCreator.createPlanet(any())).thenReturn(createPlanetVO());
        byte[] json = convertObjectToJsonBytes(createPlanetVO());

        mockMvc.perform(post("/planet").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isCreated());
    }

    @Test
    public void createPlanetNotFound() throws Exception {
        Mockito.when(starWarsPlanetCreator.createPlanet(any())).thenThrow(PlanetNotFoundException.class);
        byte[] json = convertObjectToJsonBytes(createPlanetVO());

        mockMvc.perform(post("/planet").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPlanetDataUninformed() throws Exception {
        Mockito.when(starWarsPlanetCreator.createPlanet(any())).thenThrow(PlanetDataUninformedException.class);
        byte[] json = convertObjectToJsonBytes(createPlanetVO());

        mockMvc.perform(post("/planet").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createPlanetUninformed() throws Exception {
        Mockito.when(starWarsPlanetCreator.createPlanet(any())).thenThrow(PlanetUninformedException.class);
        byte[] json = convertObjectToJsonBytes(createPlanetVO());

        mockMvc.perform(post("/planet").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createPlanetAlreadyExists() throws Exception {
        Mockito.when(starWarsPlanetCreator.createPlanet(any())).thenThrow(PlanetAlreadyExistsException.class);
        byte[] json = convertObjectToJsonBytes(createPlanetVO());

        mockMvc.perform(post("/planet").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isConflict());
    }
}
