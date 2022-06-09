package com.company.musicstorerecommendations.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="track_recommendation")
public class TrackRecommendations {

    @Id
    @Column(name = "track_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trackRecommendationId;
    @Column(name="track_id")
    private Integer trackId;
    @Column(name="user_id")
    private Integer userId;
    private boolean liked;

    public TrackRecommendations(Integer trackRecommendationId, Integer trackId, Integer userId, boolean liked) {
        this.trackRecommendationId = trackRecommendationId;
        this.trackId = trackId;
        this.userId = userId;
        this.liked = liked;
    }

    public TrackRecommendations() {
    }

    public Integer getTrackRecommendationId() {
        return trackRecommendationId;
    }

    public void setTrackRecommendationId(Integer trackRecommendationId) {
        this.trackRecommendationId = trackRecommendationId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
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
        TrackRecommendations that = (TrackRecommendations) o;
        return liked == that.liked && Objects.equals(trackRecommendationId, that.trackRecommendationId) && Objects.equals(trackId, that.trackId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackRecommendationId, trackId, userId, liked);
    }

    @Override
    public String toString() {
        return "TrackRecommendations{" +
                "trackRecommendationId=" + trackRecommendationId +
                ", trackId=" + trackId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
