package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.model.LabelRecommendations;
import com.company.musicstorerecommendations.repository.LabelRecommendationsRepository;
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
@WebMvcTest(LabelRecommendationsController.class)
public class LabelRecommendationsControllerTest {

    @MockBean
    LabelRecommendationsRepository labelRecommendationsRepository;

    private LabelRecommendations inputLabelRecommendations;
    private LabelRecommendations outputLabelRecommendations;
    private String inputLabelRecommendationsString;
    private String outputLabelRecommendationsString;

    private List<LabelRecommendations> allLabelRecommendationss;
    private String allLabelRecommendationssString;
    private int labelRecommendationsId = 3;
    private int nonExistentLabelRecommendationsId = 511;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        inputLabelRecommendations = new LabelRecommendations(3, 8, 5, false);
        outputLabelRecommendations = new LabelRecommendations(3, 8, 5, false);
        inputLabelRecommendationsString = mapper.writeValueAsString(inputLabelRecommendations);
        outputLabelRecommendationsString = mapper.writeValueAsString(outputLabelRecommendations);
        allLabelRecommendationss = Arrays.asList(outputLabelRecommendations);
        allLabelRecommendationssString = mapper.writeValueAsString(allLabelRecommendationss);

        when(labelRecommendationsRepository.save(inputLabelRecommendations)).thenReturn(outputLabelRecommendations);
        when(labelRecommendationsRepository.findAll()).thenReturn(allLabelRecommendationss);
        when(labelRecommendationsRepository.findById(labelRecommendationsId)).thenReturn(Optional.of(outputLabelRecommendations));


    }

    @Test
    public void shouldCreateLabel() throws Exception {
        mockMvc.perform(post("/labelRecommendations/")
                        .content(inputLabelRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelRecommendationsString));
    }

    @Test
    public void shouldGetAllLabels() throws Exception {
        mockMvc.perform(get("/labelRecommendations/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allLabelRecommendationssString));
    }

    @Test
    public void shouldGetLabelById() throws Exception {
        mockMvc.perform(get("/labelRecommendations/" + labelRecommendationsId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputLabelRecommendationsString));
    }

    @Test
    public void shouldReport404WhenFindLabelByInvalidId() throws Exception {
        mockMvc.perform(get("/labelRecommendations/" + nonExistentLabelRecommendationsId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldUpdateLabel() throws Exception {
        mockMvc.perform(put("/labelRecommendations/" + labelRecommendationsId)
                        .content(outputLabelRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldBeWrongLabelIdWhenPutRequestContainsNonMatchingIds() throws Exception {
        mockMvc.perform(put("/labelRecommendations/" + nonExistentLabelRecommendationsId)
                        .content(outputLabelRecommendationsString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteLabel() throws Exception {
        mockMvc.perform(delete("/labelRecommendations/" + labelRecommendationsId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
