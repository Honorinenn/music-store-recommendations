package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.model.LabelRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRecommendationsRepository extends JpaRepository<LabelRecommendations, Integer> {

}
