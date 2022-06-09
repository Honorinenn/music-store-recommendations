package com.company.musicstorerecommendations.controller;


import com.company.musicstorerecommendations.exception.WrongTrackRecommendationsIdException;
import com.company.musicstorerecommendations.model.TrackRecommendations;
import com.company.musicstorerecommendations.repository.TrackRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class TrackRecommendationsController {
    @Autowired
    private TrackRecommendationsRepository repo;

    @PostMapping("/trackRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendations createTrackRecommendations(@RequestBody TrackRecommendations trackRecommendations) {return repo.save(trackRecommendations);}

    @GetMapping("/trackRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendations> getEveryLastTrackRecommendations(){
        return repo.findAll();
    }

    @GetMapping("/trackRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TrackRecommendations getTrackRecommendationsById(@PathVariable int id) {
        Optional<TrackRecommendations> optionalTrackRecommendations = repo.findById(id);
        if (optionalTrackRecommendations.isPresent() == false) {
            throw new WrongTrackRecommendationsIdException("no track found with id" + id);
        }
        return optionalTrackRecommendations.get();
    }

    @PutMapping("/trackRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendations(@PathVariable int id, @RequestBody TrackRecommendations trackRecommendations){
        if (trackRecommendations.getTrackId() == null) {
            trackRecommendations.setTrackId(id);
        } else if (trackRecommendations.getTrackId() != id) {
            throw new WrongTrackRecommendationsIdException("The id in your path (" + id + ")  is " + "different from the id in your body ("+ trackRecommendations.getTrackId() + ").");
        }
        repo.save(trackRecommendations);
    }


    @DeleteMapping("/trackRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTrackRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }

}
