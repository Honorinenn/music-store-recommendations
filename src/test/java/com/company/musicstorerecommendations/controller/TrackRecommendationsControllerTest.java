package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.model.TrackRecommendations;
import com.company.musicstorerecommendations.repository.TrackRecommendationsRepository;
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
@WebMvcTest(TrackRecommendationsController.class)
public class TrackRecommendationsControllerTest {


    @MockBean
    TrackRecommendationsRepository trackRecommendationsRepository;

    private TrackRecommendations inputTrackRecommendations;
    private TrackRecommendations outputTrackRecommendations;
    private String inputTrackRecommendationsString;
    private String outputTrackRecommendationsString;

    private List<TrackRecommendations> allTrackRecommendationss;
    private String allTrackRecommendationssString;
    private int trackRecommendationsId = 4;
    private int nonExistentTrackRecommendationsId = 601;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        inputTrackRecommendations = new TrackRecommendations(4, 204, 304, true);

        outputTrackRecommendations = new TrackRecommendations(4, 4, 304, true);
        inputTrackRecommendationsString = mapper.writeValueAsString(inputTrackRecommendations);
        outputTrackRecommendationsString = mapper.writeValueAsString(outputTrackRecommendations);
        allTrackRecommendationss = Arrays.asList(outputTrackRecommendations);
        allTrackRecommendationssString = mapper.writeValueAsString(allTrackRecommendationss);

        when(trackRecommendationsRepository.save(inputTrackRecommendations)).thenReturn(outputTrackRecommendations);
        when(trackRecommendationsRepository.findAll()).thenReturn(allTrackRecommendationss);
        when(trackRecommendationsRepository.findById(trackRecommendationsId)).thenReturn(Optional.of(outputTrackRecommendations));
    }

    @Test
    public void shouldCreateTrack() throws Exception {
        mockMvc.perform(post("/trackRecommendations")
                        .content(inputTrackRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackRecommendationsString));
    }

    @Test
    public void shouldGetAllTracks() throws Exception {
        mockMvc.perform(get("/trackRecommendations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allTrackRecommendationssString));
    }

    @Test
    public void shouldGetTrackRecommendationsById() throws Exception {
        mockMvc.perform(get("/trackRecommendations/" + trackRecommendationsId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTrackRecommendationsString));
    }

    @Test
    public void shouldReport404WhenFindTrackByInvalidId() throws Exception {
        mockMvc.perform(get("/trackRecommendations/" + nonExistentTrackRecommendationsId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateTrackRecommendations() throws Exception {
        mockMvc.perform(put("/trackRecommendations/" + trackRecommendationsId)
                        .content(outputTrackRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeWrongTrackRecommendationsIdWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/trackRecommendations/" + nonExistentTrackRecommendationsId)
                        .content(outputTrackRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTrackRecommendations() throws Exception {
        mockMvc.perform(delete("/trackRecommendations/" + trackRecommendationsId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }




}
