package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.AlbumRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  AlbumRecommendationsRepository extends JpaRepository<AlbumRecommendations, Integer> {
}
