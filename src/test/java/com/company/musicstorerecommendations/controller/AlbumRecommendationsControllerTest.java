package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.AlbumRecommendations;
import com.company.musicstorerecommendations.repository.AlbumRecommendationsRepository;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationsController.class)
public class AlbumRecommendationsControllerTest {

    @MockBean
    AlbumRecommendationsRepository albumRecommendationsRepository;

    private AlbumRecommendations inputAlbumRecommendations;
    private AlbumRecommendations outputAlbumRecommendations;
    private String inputAlbumRecommendationsString;
    private String outputAlbumRecommendationsString;

    private List<AlbumRecommendations> allAlbumRecommendationss;
    private String allAlbumRecommendationssString;
    private int albumRecommendationsId = 4;
    private int nonExistentAlbumRecommendationsId = 601;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        inputAlbumRecommendations = new AlbumRecommendations(4, 32, 106, true);

        outputAlbumRecommendations = new AlbumRecommendations(4, 32, 106, true);
        inputAlbumRecommendationsString = mapper.writeValueAsString(inputAlbumRecommendations);
        outputAlbumRecommendationsString = mapper.writeValueAsString(outputAlbumRecommendations);
        allAlbumRecommendationss = Arrays.asList(outputAlbumRecommendations);
        allAlbumRecommendationssString = mapper.writeValueAsString(allAlbumRecommendationss);

        when(albumRecommendationsRepository.save(inputAlbumRecommendations)).thenReturn(outputAlbumRecommendations);
        when(albumRecommendationsRepository.findAll()).thenReturn(allAlbumRecommendationss);
        when(albumRecommendationsRepository.findById(albumRecommendationsId)).thenReturn(Optional.of(outputAlbumRecommendations));
    }

    @Test
    public void shouldCreateAlbum() throws Exception {
        mockMvc.perform(post("/albumRecommendations")
                        .content(inputAlbumRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumRecommendationsString));
    }

    @Test
    public void shouldGetAllAlbums() throws Exception {
        mockMvc.perform(get("/albumRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allAlbumRecommendationssString));
    }

    @Test
    public void shouldGetAlbumById() throws Exception {
        mockMvc.perform(get("/albumRecommendations/" + albumRecommendationsId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputAlbumRecommendationsString));
    }

    @Test
    public void shouldReport404WhenFindAlbumRecommendationByInvalidId() throws Exception {
        mockMvc.perform(get("/albumRecommendations/" + nonExistentAlbumRecommendationsId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateAlbum() throws Exception {
        mockMvc.perform(put("/albumRecommendations/" + albumRecommendationsId)
                        .content(outputAlbumRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeWrongAlbumIdExceptionWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/albumRecommendations/" + nonExistentAlbumRecommendationsId)
                        .content(outputAlbumRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteArtist() throws Exception {
        mockMvc.perform(delete("/albumRecommendations/" + albumRecommendationsId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }



}