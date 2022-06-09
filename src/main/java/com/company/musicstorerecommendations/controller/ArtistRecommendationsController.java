package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.exception.WrongArtistRecommendationsIdException;
import com.company.musicstorerecommendations.model.ArtistRecommendations;
import com.company.musicstorerecommendations.repository.ArtistRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ArtistRecommendationsController {
    @Autowired
    private ArtistRecommendationsRepository repo;

    @PostMapping("/artistRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendations createArtistRecommendations(@RequestBody ArtistRecommendations artistRecommendations){
        return repo.save(artistRecommendations);
    }

    @GetMapping("/artistRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendations> getEveryLastArtistRecommendations(){
        return repo.findAll();
    }

    @GetMapping("/artistRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistRecommendations getArtistRecommendationsById(@PathVariable int id) {
        Optional<ArtistRecommendations> optionalArtistRecommendations = repo.findById(id);
        if (optionalArtistRecommendations.isPresent() == false) {
            throw new WrongArtistRecommendationsIdException("no artist found with id" + id);
        }
        return optionalArtistRecommendations.get();
    }

    @PutMapping("/artistRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendations(@PathVariable int id, @RequestBody ArtistRecommendations artistRecommendations){
        if (artistRecommendations.getArtistRecommendationId() == null) {
            artistRecommendations.setArtistRecommendationId(id);
        } else if (artistRecommendations.getArtistRecommendationId( )!= id) {
            throw new WrongArtistRecommendationsIdException("The id in your path (" + id + ")  is " + "different from the id in your body ("+ artistRecommendations.getArtistId() + ").");
        }
        repo.save(artistRecommendations);
    }


    @DeleteMapping("/artistRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeArtistRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }



}
