package com.company.musicstorerecommendations.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="label_recommendation")
public class LabelRecommendations {
    @Id
    @Column(name = "label_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer labelRecommendationId;
    @Column(name="label_id")
    private Integer labelId;
    @Column(name="user_id")
    private Integer userId;
    private boolean liked;

    public LabelRecommendations(Integer labelRecommendationId, Integer labelId, Integer userId, boolean liked) {
        this.labelRecommendationId = labelRecommendationId;
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

    public LabelRecommendations() {
    }

    public Integer getLabelRecommendationId() {
        return labelRecommendationId;
    }

    public void setLabelRecommendationId(Integer labelRecommendationId) {
        this.labelRecommendationId = labelRecommendationId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
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
        LabelRecommendations that = (LabelRecommendations) o;
        return liked == that.liked && Objects.equals(labelRecommendationId, that.labelRecommendationId) && Objects.equals(labelId, that.labelId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelRecommendationId, labelId, userId, liked);
    }

    @Override
    public String toString() {
        return "LabelRecommendations{" +
                "labelRecommendationId=" + labelRecommendationId +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
