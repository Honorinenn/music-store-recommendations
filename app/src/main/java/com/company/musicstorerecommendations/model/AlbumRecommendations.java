package com.company.musicstorerecommendations.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="album_recommendation")
public class AlbumRecommendations {

    @Id
    @Column(name = "album_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumRecommendationId;
    @Column(name="album_id")
    private Integer albumId;
    @Column(name="user_id")
    private Integer userId;
    private boolean liked;

    public AlbumRecommendations(Integer albumRecommendationId, Integer albumId, Integer userId, boolean liked) {
        this.albumRecommendationId = albumRecommendationId;
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public AlbumRecommendations() {
    }

    public Integer getAlbumRecommendationId() {
        return albumRecommendationId;
    }

    public void setAlbumRecommendationId(Integer albumRecommendationId) {
        this.albumRecommendationId = albumRecommendationId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
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
        AlbumRecommendations that = (AlbumRecommendations) o;
        return liked == that.liked && Objects.equals(albumRecommendationId, that.albumRecommendationId) && Objects.equals(albumId, that.albumId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumRecommendationId, albumId, userId, liked);
    }

    @Override
    public String toString() {
        return "AlbumRecommendations{" +
                "albumRecommendationId=" + albumRecommendationId +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
