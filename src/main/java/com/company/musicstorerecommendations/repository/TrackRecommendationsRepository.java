package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.TrackRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRecommendationsRepository extends JpaRepository<TrackRecommendations, Integer> {
}
