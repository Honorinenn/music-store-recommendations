package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.model.ArtistRecommendations;
import com.company.musicstorerecommendations.repository.ArtistRecommendationsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistRecommendationsController.class)
public class ArtistRecommendationsControllerTest {

    @MockBean
    ArtistRecommendationsRepository artistRecommendationsRepository;

    private ArtistRecommendations inputArtistRecommendations;
    private ArtistRecommendations outputArtistRecommendations;
    private String inputArtistRecommendationsString;
    private String outputArtistRecommendationsString;

    private List<ArtistRecommendations> allArtistRecommendationss;
    private String allArtistRecommendationssString;
    private int artistRecommendationsId = 3;
    private int nonExistentArtistRecommendationsId = 999;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        inputArtistRecommendations = new ArtistRecommendations(3, 13, 18 ,false);
        outputArtistRecommendations = new ArtistRecommendations(3, 13, 18, false );
        inputArtistRecommendationsString = mapper.writeValueAsString(inputArtistRecommendations);
        outputArtistRecommendationsString = mapper.writeValueAsString(outputArtistRecommendations);
        allArtistRecommendationss = Arrays.asList(outputArtistRecommendations);
        allArtistRecommendationssString = mapper.writeValueAsString(allArtistRecommendationss);

        when(artistRecommendationsRepository.save(inputArtistRecommendations)).thenReturn(outputArtistRecommendations);
        when(artistRecommendationsRepository.findAll()).thenReturn(allArtistRecommendationss);
        when(artistRecommendationsRepository.findById(artistRecommendationsId)).thenReturn(Optional.of(outputArtistRecommendations));


    }

    @Test
    public void shouldCreateArtist() throws Exception {
        mockMvc.perform(post("/artistRecommendations")
                        .content(inputArtistRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistRecommendationsString));
    }

    @Test
    public void shouldGetAllArtists() throws Exception {
        mockMvc.perform(get("/artistRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allArtistRecommendationssString));
    }

    @Test
    public void shouldGetArtistById() throws Exception {
        mockMvc.perform(get("/artistRecommendations/" + artistRecommendationsId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputArtistRecommendationsString));
    }


    @Test
    public void shouldReport404WhenFindArtistByInvalidId() throws Exception {
        mockMvc.perform(get("/artistRecommendations/" + nonExistentArtistRecommendationsId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldUpdateArtist() throws Exception {
        mockMvc.perform(put("/artistRecommendations/" + artistRecommendationsId)
                        .content(outputArtistRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeWrongArtistIdWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/artistRecommendations/" + nonExistentArtistRecommendationsId)
                        .content(outputArtistRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteArtist() throws Exception {
        mockMvc.perform(delete("/artistRecommendations/" + artistRecommendationsId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
