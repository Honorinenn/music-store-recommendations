package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.exception.WrongLabelRecommendationsIdException;
import com.company.musicstorerecommendations.model.LabelRecommendations;
import com.company.musicstorerecommendations.repository.LabelRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class LabelRecommendationsController {
    @Autowired
    private LabelRecommendationsRepository repo;

    @PostMapping("/labelRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendations createLabel(@RequestBody LabelRecommendations labelRecommendations){
        return repo.save(labelRecommendations);
    }

    @GetMapping("/labelRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendations> getEveryLastLabelRecommendations(){
        return repo.findAll();
    }

    @GetMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelRecommendations getLabelById(@PathVariable int id) {
        Optional<LabelRecommendations> optionalLabelRecommendations = repo.findById(id);
        if (optionalLabelRecommendations.isPresent() == false) {
            throw new WrongLabelRecommendationsIdException("no label found with id" + id);
        }
        return optionalLabelRecommendations.get();
    }

    @PutMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendations(@PathVariable int id, @RequestBody LabelRecommendations labelRecommendations){
        if (labelRecommendations.getLabelRecommendationId() == null) {
            labelRecommendations.setLabelRecommendationId(id);
        } else if (labelRecommendations.getLabelRecommendationId( )!= id) {
            throw new WrongLabelRecommendationsIdException("The id in your path (" + id + ")  is " + "different from the id in your body ("+ labelRecommendations.getLabelId() + ").");
        }
        repo.save(labelRecommendations);
    }


    @DeleteMapping("/labelRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeLabelRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }



}
