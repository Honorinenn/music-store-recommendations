package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.exception.WrongAlbumRecommendationsIdException;
import com.company.musicstorerecommendations.model.AlbumRecommendations;
import com.company.musicstorerecommendations.repository.AlbumRecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class AlbumRecommendationsController {

    @Autowired
    private AlbumRecommendationsRepository repo;

    @PostMapping("/albumRecommendations")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendations createAlbum(@RequestBody AlbumRecommendations albumRecommendations){
        return repo.save(albumRecommendations);
    }

    @GetMapping("/albumRecommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendations> getEveryLastAlbumRecommendations(){
        return repo.findAll();
    }

    @GetMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumRecommendations getAlbumRecommendationsById(@PathVariable int id) {
        Optional<AlbumRecommendations> optionalAlbumRecommendations = repo.findById(id);
        if (optionalAlbumRecommendations.isPresent() == false) {
            throw new WrongAlbumRecommendationsIdException("no album found with id" + id);
        }
        return optionalAlbumRecommendations.get();
    }



    @PutMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendations(@PathVariable int id, @RequestBody AlbumRecommendations albumRecommendations){
        if (albumRecommendations.getAlbumRecommendationId() == null) {
            albumRecommendations.setAlbumRecommendationId(id);
        } else if (albumRecommendations.getAlbumRecommendationId()!= id) {
            throw new WrongAlbumRecommendationsIdException("The id in your path (" + id + ")  is " + "different from the id in your body ("+ albumRecommendations.getAlbumId() + ").");
        }
        repo.save(albumRecommendations);
    }


    @DeleteMapping("/albumRecommendations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAlbumRecommendationsFromInventory(@PathVariable int id) {
        repo.deleteById(id);
    }



}
