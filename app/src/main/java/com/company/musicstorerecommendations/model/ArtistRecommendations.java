package com.company.musicstorerecommendations.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="artist_recommendation")
public class ArtistRecommendations {

    @Id
    @Column(name = "artist_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistRecommendationId;
    @Column(name="artist_id")
    private Integer artistId;
    @Column(name="user_id")
    private Integer userId;
    private boolean liked;

    public ArtistRecommendations(Integer artistRecommendationId, Integer artistId, Integer userId, boolean liked) {
        this.artistRecommendationId = artistRecommendationId;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public ArtistRecommendations() {
    }

    public Integer getArtistRecommendationId() {
        return artistRecommendationId;
    }

    public void setArtistRecommendationId(Integer artistRecommendationId) {
        this.artistRecommendationId = artistRecommendationId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistRecommendations that = (ArtistRecommendations) o;
        return liked == that.liked && Objects.equals(artistRecommendationId, that.artistRecommendationId) && Objects.equals(artistId, that.artistId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistRecommendationId, artistId, userId, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecommendations{" +
                "artistRecommendationId=" + artistRecommendationId +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
